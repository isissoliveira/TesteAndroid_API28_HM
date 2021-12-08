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
/*
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
*/



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
            LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location2%2Fhangar_principal2.jpg?alt=media&token=e33969e1-8e4b-4db3-bd20-f107569b4eb4"
                ,"2", true) ,
            LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location3%2Fpelicano_principal.jpg?alt=media&token=8831b783-4759-4b1c-a66b-cc5136000098"
                ,"3", true) ,
            LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location4%2Fkpopka_prinicpal2.jpg?alt=media&token=509b3b3e-f293-42dc-9865-c38e72959921"
                ,"4", true) ,
            LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location5%2Fbaianera-principal2.jpg?alt=media&token=65eafd15-2293-4d86-8f82-b9c27962b73f"
                ,"5", true) ,
            LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location6%2Fgarage_principal.jpg?alt=media&token=1f7fa7ee-29a2-43ee-8738-cb7262c376c1"
                ,"6", true) ,
            LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location7%2Fpassai_principal2.jpg?alt=media&token=9752d49c-f3ed-4df8-864b-f7338f7e8bc3"
                ,"7", true) ,
            LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location8%2Fsimple_principal.jpg?alt=media&token=45032b14-e1f4-49a6-bb9a-062b732dcf0b"
                ,"8", true) ,
            LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location9%2Fkitsume_principal.jpg?alt=media&token=c7983849-85cd-4dea-93a4-4818f0134b2c"
                ,"9", true) ,
            LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location10%2Fcantina_principal.jpg?alt=media&token=f73b52c4-9d9d-4403-9f4f-a5f94767bd17"
                ,"10", true) ,
            LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location11%2Farep-principal2.jpg?alt=media&token=e60ed71e-5352-4069-9d13-4374a2bd54ec"
                ,"11", true) ,
            LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location12%2Ftaqueria-principal2.jpg?alt=media&token=a3bc518c-572e-4534-8a37-6da873d15c64"
                ,"12", true) ,
            LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location13%2Fpub-principal2.jpg?alt=media&token=3e25c965-981a-444f-b8a6-73f9dedfbcb8"
                ,"13", true) ,
            LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location14%2Ffire-principal2.jpg?alt=media&token=85d6d216-e5cf-455c-85c8-bbddf24a73ef"
                ,"14", true) ,
            LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location15%2Fbbq-principal.jpg?alt=media&token=0bab1ca5-ec2a-4a33-8545-169f0717585f"
                ,"15", true),

            LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location1%2Fcafe-6.jfif?alt=media&token=3d0a02f3-59aa-4890-804d-a29bae1e75ea"
                ,"1", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location1%2Fcafe_1.jpg?alt=media&token=067db0e4-ef21-4ec7-b745-9c5b0c2633e1"
                ,"1", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location1%2Fcafe_2.jpg?alt=media&token=b6dee776-2945-4ac1-bcdc-48fc00508b32"
                ,"1", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location1%2Fcafe_3.jpg?alt=media&token=e4a996dd-84c2-44bf-afdc-f798ba96f1ce"
                ,"1", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location1%2Fcafe_4.jpg?alt=media&token=656bb6f6-c5dc-4007-b751-5774336af37e"
                ,"1", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location1%2Fcafe_5.png?alt=media&token=240e7ea3-f0c5-4c2a-a2d4-830e90c5b073"
                ,"1", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location2%2Fhangar1.jpg?alt=media&token=0eb0eb8e-4ce4-42a1-9dcf-65f84189eaff"
                ,"2", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location2%2Fhangar2.jpg?alt=media&token=63608ad5-b998-4a7c-91a9-f6ac33cbb5ea"
                ,"2", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location2%2Fhangar3.jpg?alt=media&token=28f35645-9be6-4139-8116-6c108e871fd9"
                ,"2", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location2%2Fhangar4.jpg?alt=media&token=cb004617-c0a8-4c29-a937-2417583aced2"
                ,"2", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location2%2Fhangar5.jpg?alt=media&token=fa4310a9-1393-409b-ac9c-9d7a28856c47"
                ,"2", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location2%2Fhangar6.jpg?alt=media&token=9bdaed32-0c08-4943-aad3-38fdad04eb2b"
                ,"2", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location3%2Fpelicano1.jpg?alt=media&token=e44ed74d-f1a6-441d-becd-3b5773d5d52c"
                ,"3", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location3%2Fpelicano2.jpg?alt=media&token=b7dc11c8-3b80-4776-8c7b-54c90f7745e5"
                ,"3", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location3%2Fpelicano3.jpg?alt=media&token=269f1881-c823-46a1-99b1-3762ea1eb11b"
                ,"3", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location3%2Fpelicano4.jpg?alt=media&token=f00706d6-4d70-4c47-85c0-6887babf841a"
                ,"3", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location3%2Fpelicano5.jpg?alt=media&token=42988d74-3305-471a-8593-3eb4bdb64207"
                ,"3", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location3%2Fpelicano6.jpg?alt=media&token=dc68c856-bbe0-492e-b7c7-29f48d5902ce"
                ,"3", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location4%2Fkpopka_1.jpg?alt=media&token=57d77a51-9727-4ef1-a33a-af8656e10cf0"
                ,"4", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location4%2Fkpopka_2.jpg?alt=media&token=2459fa45-0dd1-4118-a71d-46c7dd791bc4"
                ,"4", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location4%2Fkpopka_3.jpg?alt=media&token=973abb4f-eb43-472a-8c3f-169d6106655a"
                ,"4", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location4%2Fkpopka_4.jpg?alt=media&token=6def93c4-376b-4c49-a574-0ea403cbde5f"
                ,"4", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location4%2Fkpopka_5.jpg?alt=media&token=6f60b6aa-aa25-44e4-9f98-a14244515d26"
                ,"4", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location4%2Fkpopka_6.jpg?alt=media&token=2cf2d0f2-6294-4b13-82d9-ce1d25ab1b33"
                ,"4", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location5%2Fbaianera-1.jpg?alt=media&token=e7e87d7c-cdb6-4c93-9aff-664b02b17e3a"
                ,"5", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location5%2Fbaianera-2.jpg?alt=media&token=cb0981da-3d27-497a-957a-e63206d5b805"
                ,"5", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location5%2Fbaianera-3.jpg?alt=media&token=bc9e9a6b-a408-4f0a-9109-a15e92524e0f"
                ,"5", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location5%2Fbaianera-4.jpg?alt=media&token=8865702d-3b89-478d-bf91-1c882fa90956"
                ,"5", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location5%2Fbaianera-5.jpg?alt=media&token=ca154c41-e1d1-46ab-9d5a-cced33700165"
                ,"5", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location6%2Fgarage_1.jpg?alt=media&token=ef2c5900-7c8c-4151-bf9f-f371c8e6c4a3"
                ,"6", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location6%2Fgarage_2.jpg?alt=media&token=cd1feeee-98cc-4d24-9a93-3e6d44da0a6e"
                ,"6", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location6%2Fgarage_3.jpg?alt=media&token=1e5e0b91-7699-43d9-8fe5-45ce7cc6be6a"
                ,"6", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location6%2Fgarage_4.jpg?alt=media&token=ac5abe47-93e2-40ea-9a11-d153e485b10d"
                ,"6", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location6%2Fgarage_5.jpg?alt=media&token=596cb158-b5b1-4173-8a55-a9d33ad81539"
                ,"6", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location6%2Fgarage_6.jpg?alt=media&token=25b8034f-b70f-49fb-af6f-7210c5d9eaa8"
                ,"6", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location7%2Fpassai_1.jpg?alt=media&token=eaa7070e-9c13-4404-af7e-bcf7b42ae5b4"
                ,"7", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location7%2Fpassai_2.jpg?alt=media&token=f0d240d3-163e-4cb1-82a0-186c1cd04769"
                ,"7", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location7%2Fpassai_3.jpg?alt=media&token=36c690c8-61eb-4dbf-90eb-ba21142f5c17"
                ,"7", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location7%2Fpassai_4.jpg?alt=media&token=022d5387-9a04-4ea5-a172-4c9854eb98a0"
                ,"7", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location7%2Fpassai_5.jpg?alt=media&token=55a9c25f-f002-401d-8388-ddeed9e45af6"
                ,"7", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location7%2Fpassai_6.jpg?alt=media&token=255310c1-c97b-4193-a1a9-bb46ef3bbe5e"
                ,"7", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location8%2Fsimple_1.jpg?alt=media&token=d4eb3aed-c263-47c8-a4f7-6aa8eec9634a"
                ,"8", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location8%2Fsimple_2.jpg?alt=media&token=4e7588dc-2cda-4088-a648-1728665a6557"
                ,"8", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location8%2Fsimple_3.jpg?alt=media&token=8fe6bdb2-e9cc-4383-b800-768a07b9a946"
                ,"8", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location8%2Fsimple_4.jpg?alt=media&token=37619529-d14d-4f66-8ce4-d49af2e36835"
                ,"8", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location8%2Fsimple_5.jpg?alt=media&token=8b0ea232-9803-4857-8085-f5c7edf3b2cb"
                ,"8", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location8%2Fsimple_6.jpg?alt=media&token=b9740d31-7b34-41ab-a375-383793692e00"
                ,"8", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location9%2Fkitsume_1.jpg?alt=media&token=7b66c1dc-0b6b-4369-8017-9c4c1eae7491"
                ,"9", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location9%2Fkitsume_2.jpg?alt=media&token=1b4f2df9-c5f2-45d4-a5f8-b23dc4bf0cd9"
                ,"9", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location9%2Fkitsume_3.jpg?alt=media&token=db740021-d680-4db9-801c-600ee29d823f"
                ,"9", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location9%2Fkitsume_4.jpg?alt=media&token=81dd3c71-1d53-4b1e-b806-07092fa73fb1"
                ,"9", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location9%2Fkitsume_5.jpg?alt=media&token=de89fc82-9356-490a-8e8b-9a5e1cd5f5f4"
                ,"9", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location9%2Fkitsume_6.jpg?alt=media&token=40f0ea80-e618-415d-9179-dcde4c6c5bbf"
                ,"9", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location10%2Fcantina_1.jpg?alt=media&token=48758d1e-b19a-4a16-882f-302bfec26a09"
                ,"10", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location10%2Fcantina_2.jpg?alt=media&token=37e40f76-9676-4c0c-bcc1-2505a7b1ef21"
                ,"10", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location10%2Fcantina_3.jpg?alt=media&token=ff0456ad-77ca-411b-9e2e-20b45dd9b569"
                ,"10", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location10%2Fcantina_4.jpg?alt=media&token=dafc7c20-882d-4434-a69d-e91fb622abfd"
                ,"10", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location10%2Fcantina_5.jpg?alt=media&token=13ab473e-bbec-4f25-bac0-b209321e6943"
                ,"10", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location10%2Fcantina_6.jpg?alt=media&token=501c06ff-6667-4c09-a48b-5b8550ee1ec2"
                ,"10", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location11%2Farep-1.jpg?alt=media&token=14f5367c-f335-4f94-a8f0-97e1a97dad05"
                ,"11", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location11%2Farep-2.jpg?alt=media&token=050e0bdc-4074-48e1-bea8-7c02097281cd"
                ,"11", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location11%2Farep-3.jpg?alt=media&token=52572a44-ad1c-4c15-bc82-426b2af6ab6a"
                ,"11", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location11%2Farep-4.jpg?alt=media&token=16641e38-f834-42ef-a814-70716967a000"
                ,"11", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location12%2Ftaqueria-1.jpg?alt=media&token=542e5c80-8b83-4360-ab0e-8364b8517004"
                ,"12", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location12%2Ftaqueria-2.jpg?alt=media&token=ea9f3f43-818b-4c9a-8999-81879a01a667"
                ,"12", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location12%2Ftaqueria-3.jpg?alt=media&token=f007e388-d71c-4277-a279-6160d5f0bf70"
                ,"12", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location12%2Ftaqueria-4.jpg?alt=media&token=48ad5b7e-d203-47ca-ad86-814276bb5c23"
                ,"12", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location12%2Ftaqueria-5.jpg?alt=media&token=2194b799-b779-44c1-a494-f73956aca75f"
                ,"12", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location13%2Fpub-1.jpg?alt=media&token=b2285ffe-ddc0-4afc-91bb-041b3921a7c7"
                ,"13", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location13%2Fpub-2.jpg?alt=media&token=2b84427d-ae8e-44b2-a759-2097e9726042"
                ,"13", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location13%2Fpub-3.jpg?alt=media&token=9ec76d75-ab16-407e-9711-464ef250028f"
                ,"13", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location13%2Fpub-4.jpg?alt=media&token=47fc9110-abfb-490f-9ed9-814e465efab1"
                ,"13", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location14%2Ffire-1.jpg?alt=media&token=09741fb0-e76c-4223-b47f-0e634b15e880"
                ,"14", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location14%2Ffire-2.jpg?alt=media&token=cfaa866d-b0ad-42a2-8038-76ede7b49eb0"
                ,"14", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location14%2Ffire-3.jpg?alt=media&token=7675260f-2235-4969-991e-3c3fe760d6a2"
                ,"14", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location14%2Ffire-4.jpg?alt=media&token=fda2d1e3-fc0f-447c-9855-ef7d0ffa22a0"
                ,"14", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location14%2Ffire-5.jpg?alt=media&token=36a866d3-cb2c-421e-b538-7394263e2c51"
                ,"14", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location14%2Ffire-6.jpg?alt=media&token=f5f486cc-a709-484f-a4c3-e042318decb8"
                ,"14", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location15%2Fbbq-1.jpg?alt=media&token=f20bc0a4-678e-406d-a2a3-d9819363c3bc"
                ,"15", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location15%2Fbbq-2.jpg?alt=media&token=82225105-6edd-4c2c-ae83-08552470fb4c"
                ,"15", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location15%2Fbbq-3.jpg?alt=media&token=44935c2d-4ac2-4ac7-8c5e-eead71139b2d"
                ,"15", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location15%2Fbbq-4.jpg?alt=media&token=0a7b9b30-00a8-4f6e-9e2c-d27e67fa2de6"
                ,"15", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location15%2Fbbq-5.jpg?alt=media&token=fc915130-dbd0-4da6-81f1-fe1c2be2d3d7"
                ,"15", false)
            , LocationImage("https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Location15%2Fbbq-6.jpg?alt=media&token=ad744d27-11c4-4a6c-b68a-0116846f44b8"
                ,"15", false)

        )

        for( (i,imagem) in imagens.withIndex()){
            cadastraImagem( imagem, i.toString())
        }
    }


}