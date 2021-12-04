package hotmart.android.recyclerapplication.data

import hotmart.android.recyclerapplication.R
import hotmart.android.recyclerapplication.model.Afirmacao
import java.net.URL

class Datasource {

    fun buscaAfirmacoes(): List<Afirmacao>{
        return listOf<Afirmacao>(
            Afirmacao( R.string.afirmacao1, R.drawable.image1),
            Afirmacao( R.string.afirmacao2, R.drawable.image2),
            Afirmacao( R.string.afirmacao3, R.drawable.image3),
            Afirmacao( R.string.afirmacao4, R.drawable.image4),
            Afirmacao( R.string.afirmacao5, R.drawable.image5),
            Afirmacao( R.string.afirmacao6, R.drawable.image6),
            Afirmacao( R.string.afirmacao7, R.drawable.image7),
            Afirmacao( R.string.afirmacao8, R.drawable.image8),
            Afirmacao( R.string.afirmacao9, R.drawable.image9),
            Afirmacao( R.string.afirmacao10, R.drawable.image10)
        )
    }

}