package hotmart.android.recyclerapplication.pageObjects.Detail

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import hotmart.android.recyclerapplication.R
import hotmart.android.recyclerapplication.activity.DetailActivity
import hotmart.android.recyclerapplication.pageObjects.Home.robot.RobotDetail

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule


@RunWith(AndroidJUnit4::class)
class TesteDetail{

    private val robot = RobotDetail()
    // "@get:Rule" PREPARAÇÃO ANTES QUE OS TESTES SEJAM EXECUTADOS
    @get:Rule
    var detailActivityRule: ActivityTestRule<DetailActivity> = ActivityTestRule( DetailActivity::class.java)

    @Test
    fun testeVisualizacao() {

        robot.validaDetailBotaoHome()
        robot.validaDetailBotaoMap()
        robot.validaDetailBotaoPerfil()
        robot.validaDetailToolbar()
        robot.validaBlocoFotos()
        robot.validaBlocoSobre()
        robot.validaSchedule()
        robot.validaPhone()
        robot.validaAddress()

    }

    @Test
    fun testeVoltaHome() {
        robot.clicaBotaoHome()

        //SE TÍTULO DA PÁGINA MAIN É EXIBIDO ENTÃO O BOTÃO VOLTAR FUNCIONA
        robot.validaTituloMain()
    }
}










