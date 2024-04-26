package project;

import java.io.IOException;
import java.sql.SQLOutput;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.json.JSONObject;

public class MQTTprogram {

    private static MqttClient sampleClient;

    //opg5pgm
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String broker = "tcp://192.168.0.115:1883";
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            sampleClient = new MqttClient(broker,  MqttClient.generateClientId(), persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: " + broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");

            // put real stuff here        < -------- !!!!

            sampleClient.setCallback(new SimpleMqttCallBack());

            sampleClient.subscribe("+/grp7455/SENSOR");
            sampleClient.subscribe("+/grp7455/STATE");



            Thread.sleep(999999999);


            sampleClient.disconnect();
            System.out.println("Disconnected");
            System.exit(0);
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    //opg6pgm
    public static void publishMessage(MqttClient sampleClient,String topicsend,String content) throws MqttException {
        // Laver en publish p  sampleClient med topic topic send og indhold content.
        MqttMessage message = new MqttMessage();
        message.setPayload(content.getBytes());
        sampleClient.publish(topicsend, message);
        System.out.println("Message published");
    }

    private static class SimpleMqttCallBack implements MqttCallback {
        int status = 0;
        //opg7pgm
        public void connectionLost(Throwable throwable) {
            System.out.println("Connection to MQTT broker broke!");
        }

        boolean isSlukket = false;
        int yeet = 0;
        public void messageArrived(String s, MqttMessage mqttMessage) throws MqttException {
            String res= new String(mqttMessage.getPayload());
            // res indeholder en m ling som et JSON-object
            // put real stuff here     < --------    !!!!!!!!!! "AM2301-14":{"Temperature":24.9, "Humidity":52.4}, "TempUnit":"C"}

            if ((new JSONObject(res).has("AM2301-14"))) {
                JSONObject ting = new JSONObject(res).getJSONObject("AM2301-14");
                double temp = ting.getDouble("Temperature");
                double hum = ting.getDouble("Humidity");
                System.out.println("Times done things: " + ++yeet);
                System.out.println("temp is: " + temp);
                System.out.println("hum is: " + hum);

                if (hum <= 40 && !isSlukket) {
                    publishMessage(MQTTprogram.sampleClient, "cmnd/grp7455/Power1", "0");
                } else if (hum > 50 && isSlukket) {
                    publishMessage(MQTTprogram.sampleClient, "cmnd/grp7455/Power1", "1");
                } else
                    System.out.println("AHHHHHHHH, IT¨S BURNING");
            } else {
                String ting = new JSONObject(res).getString("POWER");
                if (ting.equals("OFF"))
                    isSlukket = true;
                else if (ting.equals("ON"))
                    isSlukket = false;
                else
                    System.out.println("UHHHH, something went wrong. POWER IS NOT RIGHT");

            }


        }

        public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
            // not used in this example
        }
    }

}
