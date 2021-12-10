package hotmart.android.recyclerapplication.pageObjects.Home.robot

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import hotmart.android.recyclerapplication.R

class RobotHome {

    fun validaTituloMain()      =  onView(withText(R.string.app_name)).check(matches(isDisplayed()))

    // SE BOTÃO HOME ESTÁ VISÍVEL NA TELA
    fun validaDetailBotaoHome()    =   onView(withId(ID_HOME_ON)).check(matches(isDisplayed()))

    // SE BOTÃO MAP ESTÁ VISÍVEL NA TELA
    fun validaDetailBotaoMap()    =   onView(withId(ID_MAP_OFF)).check(matches(isDisplayed()))

    // SE BOTÃO PROFILE ESTÁ VISÍVEL NA TELA
    fun validaDetailBotaoPerfil() =   onView(withId(ID_PROFILE_OFF)).check(matches(isDisplayed()))

    /*Kotlin não possui o recursos static do Java para atributos ou métodos de classes.
    O que existe é uma forma de você declarar um singleton da classe através do
    companion object e criar essa estrutura dentro da classe */
    companion object Ids{
        val ID_HOME_ON          = R.id.home_on
        val ID_MAP_OFF          = R.id.map_off
        val ID_PROFILE_OFF      = R.id.profile_off



    }


}