package hotmart.android.recyclerapplication.pageObjects.Home.robot

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import hotmart.android.recyclerapplication.R

class RobotDetail {

    // SE BOTÃO HOME ESTÁ VISÍVEL NA TELA
    fun validaDetailBotaoHome()    =   onView(withId(ID_HOME_ON)).check(matches(isDisplayed()))

    // SE BOTÃO MAP ESTÁ VISÍVEL NA TELA
    fun validaDetailBotaoMap()    =   onView(withId(ID_MAP_OFF)).check(matches(isDisplayed()))

    // SE BOTÃO PROFILE ESTÁ VISÍVEL NA TELA
    fun validaDetailBotaoPerfil() =   onView(withId(ID_PROFILE_OFF)).check(matches(isDisplayed()))

    // SE TOOLBAR ESTÁ VISÍVEL NA TELA
    fun validaDetailToolbar()       =   onView(withId(ID_TOOLBAR_DETAIL)).check(matches(isDisplayed()))

    // SE ESPAÇO PARA AS FOTOS ESTÁ VISÍVEL NA TELA
    fun validaBlocoFotos()      =  onView(withText(R.string.fotos)).check(matches(isDisplayed()))

    // SE ESPAÇO PARA A DESCRIÇÃO ESTÁ VISÍVEL NA TELA
    fun validaBlocoSobre()      =  onView(withText(R.string.sobre)).check(matches(isDisplayed()))

    // SE OS ÍCONES DE SCHEDULE, TELEFONE E ENDEREÇO ESTÃO VISÍVEIS NA TELA
    fun validaSchedule()        =  onView(withId(ID_SCHED)).check(matches(isDisplayed()))
    fun validaPhone()           =  onView(withId(ID_PHONE)).check(matches(isDisplayed()))
    fun validaAddress()         =  onView(withId(ID_ADDRESS)).check(matches(isDisplayed()))


    fun clicaBotaoHome()        = onView(withId(ID_HOME_ON)).perform(click())

    //VERIFICA SE TÍTULO DA PÁGINA MAIN É EXIBIDO
    fun validaTituloMain()      =  onView(withText(R.string.app_name)).check(matches(isDisplayed()))



    /*Kotlin não possui o recursos static do Java para atributos ou métodos de classes.
    O que existe é uma forma de você declarar um singleton da classe através do
    companion object e criar essa estrutura dentro da classe */
    companion object Ids{
        val ID_TOOLBAR_DETAIL   = R.id.toolbar_detail
        val ID_HOME_ON          = R.id.home_on
        val ID_MAP_OFF          = R.id.map_off
        val ID_PROFILE_OFF      = R.id.profile_off
        val ID_SCHED            = R.id.id_imageViewSched
        val ID_PHONE            = R.id.id_imageViewPhone
        val ID_ADDRESS          = R.id.id_imageViewAddress
    }



}