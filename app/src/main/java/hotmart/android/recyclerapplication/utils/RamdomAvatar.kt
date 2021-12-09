package hotmart.android.recyclerapplication.utils

import android.text.TextUtils
import kotlin.random.Random

class RamdomAvatar {

    fun male() : String{
        val lista = listOf<String>(
            "https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Avatars%2FMale%2Fmale1.png?alt=media&token=36cc8e1a-7d16-40a6-872c-b779ab462126",
            "https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Avatars%2FMale%2Fmale2.png?alt=media&token=d9328c87-81e3-40cc-83b3-7df50b6fdea1",
            "https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Avatars%2FMale%2Fmale3.png?alt=media&token=2f9ce884-8ba8-42f5-8cb1-5fdba2da580a",
            "https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Avatars%2FMale%2Fmale4.jpg?alt=media&token=590c4b48-c08e-4828-b454-a0da6ed52776",
            "https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Avatars%2FMale%2Fmale5.jpg?alt=media&token=b12a203e-6123-401d-86d7-4ef0635c3ba6",
            "https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Avatars%2FMale%2Fphoto1.png?alt=media&token=2ea8de52-6bfb-4678-8b57-1fae6ffb568a",
            "https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Avatars%2FMale%2Fphoto2.jpg?alt=media&token=7a5cddfa-df9a-4a9b-80da-cfb274dcc3cb"
        )
        val avatar =  lista[ Random.nextInt(0 , lista.size - 1 ) ]
        return avatar
    }

    fun female(): String{
        val lista = listOf<String>(
            "https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Avatars%2FFemale%2Ffemale1.png?alt=media&token=d856e713-29a7-45fc-8b55-d4e82979238a",
            "https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Avatars%2FFemale%2Ffemale2.jpg?alt=media&token=42d22030-6361-4897-ae5c-b87967017a4b",
            "https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Avatars%2FFemale%2Ffemale3.png?alt=media&token=8ef5e2e7-80d5-440a-bd3d-280ff58a01cb",
            "https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Avatars%2FFemale%2Ffemale4.png?alt=media&token=c4bb2dc6-a055-4de5-9bbb-c72375a37a22",
            "https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Avatars%2FFemale%2Ffemale5.jpg?alt=media&token=f37e6d6a-b176-426c-b9e2-2add65a6b6ea",
            "https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Avatars%2FFemale%2Ffemale6.png?alt=media&token=38f8fa8e-713a-42cd-9227-e17ace3aebb4",
            "https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Avatars%2FFemale%2Fisis1.JPG?alt=media&token=fde77ab6-17ed-4339-9817-331dcf60214e",
            "https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Avatars%2FFemale%2Fisis2.jfif?alt=media&token=7ceaa505-0241-4249-9010-95ae704ee3b9",
            "https://firebasestorage.googleapis.com/v0/b/places-732ad.appspot.com/o/Avatars%2FFemale%2Fisis3.jfif?alt=media&token=0e4ed8d2-39b9-4d21-aac6-eb3934eb80d4"
        )
        val avatar =  lista[ Random.nextInt(0 , lista.size - 1 ) ]
        return avatar
    }


}