package gui

import MainVindueController
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import java.io.IOException

object Gui : Application() {
    val MainVindueController = MainVindueController()
    @Throws(IOException::class)
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader.load<Parent>(javaClass.getClassLoader().getResource("mainVindue.fxml"))

        val scene = Scene(fxmlLoader)
        stage.title = "Zimmah"
        stage.scene = scene
        stage.show()
    }
}