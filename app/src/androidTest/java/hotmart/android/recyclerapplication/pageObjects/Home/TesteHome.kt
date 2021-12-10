package hotmart.android.recyclerapplication.pageObjects.Home

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import hotmart.android.recyclerapplication.MainActivity
import hotmart.android.recyclerapplication.pageObjects.Home.robot.RobotHome

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule


@RunWith(AndroidJUnit4::class)
class TesteHome{

    private val robot = RobotHome()
    // "@get:Rule" PREPARAÇÃO ANTES QUE OS TESTES SEJAM EXECUTADOS
    @get:Rule
    var mainActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule( MainActivity::class.java)

    @Test
    fun testeVisualizacao() {

        robot.validaTituloMain()
        robot.validaDetailBotaoHome()
        robot.validaDetailBotaoMap()
        robot.validaDetailBotaoPerfil()

    }
}










