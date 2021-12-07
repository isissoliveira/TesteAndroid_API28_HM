package hotmart.android.recyclerapplication.service

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import hotmart.android.recyclerapplication.model.LocationDetail
import hotmart.android.recyclerapplication.model.LocationImage
import java.security.MessageDigest

class FireStoreService {

    // "LAZY" - VAI SER CRIADO APENAS QUANDO O OBJETO FOR ACESSADO, CASO CONTRARIO NÃO É CRIADO
    object meuFireStore {
        val instancia by lazy { FirebaseFirestore.getInstance() }
    }

    fun buscaImagem( locationId : String, principal: Boolean ): String {
        val imagensCadastradas = meuFireStore.instancia
            .collection("location_images")
            .whereEqualTo("location_id",locationId)
            .whereEqualTo("principal", principal)
            .orderBy("location_id")

        var storages    = mutableListOf<String>()
        var storage    : String = ""

        imagensCadastradas.get()
            .addOnSuccessListener { documents ->
                if (documents != null ) {
                    for( document in documents) {
                        storage = "${document.get("storage")}"
                        storages.add( storage )
                        Log.i(ContentValues.TAG, "===============>PARA LOCATION ID [${document.get("location_id")}] :  [${storage}]=================" )
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Não foi possível obter a imagem do Storage ", exception)
            }
        Log.i(ContentValues.TAG, "===============>img [${storages.size}] ->$storage =================" )

        return storage
    }


    private fun cadastraImagem(locationImage : LocationImage, id: String){

        meuFireStore.instancia.collection("location_images")
            .document( id)
            .set(locationImage, SetOptions.merge())
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.i(ContentValues.TAG, "===============Falha ao tentar cadastrar imagem=================" )
                }
                else{
                    Log.i(ContentValues.TAG, "===============Cadastro realizado=================" )
                }
            }
    }

    fun cadastraImagens (){
        val imagens = listOf(
            LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location1%2Fcafe_principal.jpg?alt=media&token=9eb5e72d-6fc0-4e99-84b5-ea428e85dfe4"
                ,"1", true) ,
            LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location2%2Fhangar_principal.jpg?alt=media&token=dd65a5be-c89e-445c-b34b-e55824cedbbc"
                ,"2", true) ,
            LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location3%2Fpelicano_principal.jpg?alt=media&token=8831b783-4759-4b1c-a66b-cc5136000098"
                ,"3", true) ,
            LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location4%2Fkpopka_prinicpal.jpg?alt=media&token=5f555dde-ffe4-49e1-a673-e522d5d5652b"
                ,"4", true) ,
            LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location5%2Fbaianera-principal.jpg?alt=media&token=284e82d5-dbec-4e52-8792-9652cd8df9d5"
                ,"5", true) ,
            LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location6%2Fgarage_principal.jpg?alt=media&token=1f7fa7ee-29a2-43ee-8738-cb7262c376c1"
                ,"6", true) ,
            LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location7%2Fpassai_principal.jpg?alt=media&token=7a63b7c0-6c42-4be5-a61a-0f0fcababaee"
                ,"7", true) ,
            LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location8%2Fsimple_principal.jpg?alt=media&token=45032b14-e1f4-49a6-bb9a-062b732dcf0b"
                ,"8", true) ,
            LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location9%2Fkitsume_principal.jpg?alt=media&token=c7983849-85cd-4dea-93a4-4818f0134b2c"
                ,"9", true) ,
            LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location10%2Fcantina_principal.jpg?alt=media&token=f73b52c4-9d9d-4403-9f4f-a5f94767bd17"
                ,"10", true) ,
            LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location11%2Farep-principal.jpg?alt=media&token=9ac37426-0330-4087-8cdc-e2e028dcbc72"
                ,"11", true) ,
            LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location12%2Ftaqueria-principal.jpg?alt=media&token=82b7061e-c89f-4ee6-900f-a04011112dc7"
                ,"12", true) ,
            LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location13%2Fpub-principal.jpg?alt=media&token=fbb44151-2429-43f5-afc8-9208b4aa3c23"
                ,"13", true) ,
            LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location14%2Ffire-principal.jpg?alt=media&token=3688f0f2-4cb2-41e4-b9c6-99253e968b22"
                ,"14", true) ,
            LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location15%2Fbbq-principal.jpg?alt=media&token=0bab1ca5-ec2a-4a33-8545-169f0717585f"
                ,"15", true)

        )

        for( (i,imagem) in imagens.withIndex()){
            cadastraImagem( imagem, i.toString())
        }
    }


}