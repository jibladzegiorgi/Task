package com.giorgi.jibladze.football

import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso

fun BottomNavigationView.createBadge(menuItemId: Int,
                                     badgeCount:Int ) {
    this.getOrCreateBadge(menuItemId).number = badgeCount
}

inline fun <reified VM : ViewModel> Fragment.viewModelProvider(
    provider: ViewModelProvider.Factory
) =
    ViewModelProvider(this, provider).get(VM::class.java)

fun <X, Y> LiveData<X>.map(body: (X) -> Y): LiveData<Y> {
    return Transformations.map(this, body)
}

fun loadImage(
    playerImageUrl: String?,
    rightPlayerIcon: AppCompatImageView
) {
    val imageList = arrayListOf(
        "https://images2.minutemediacdn.com/image/upload/c_fill,w_912,h_516,f_auto,q_auto,g_auto/shape/cover/sport/france-v-germany-uefa-nations-league-a-5bca0ac66f0e5b6500000001.jpg",
        "https://images.cdn.fourfourtwo.com/sites/fourfourtwo.com/files/styles/image_landscape/public/hazard_madrid_quiz.jpg?itok=HzhoOFbX",
        "https://i2.wp.com/eurofootballrumours.com/wp-content/uploads/2019/09/Inter-Milan-Players-Salaries.jpg?resize=800%2C445&ssl=1",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTlevxay8FLEhv5ghxTeH6Cli2eG-HmmV3lfGsY9J6f97rCqwBBJw&s"
    )
    val randomUrl = (0 until imageList.size).random()
    Picasso.get().load(imageList[randomUrl])
        .transform(CircleTransform())
        .into(rightPlayerIcon)
}

inline fun FragmentManager.startTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commitAllowingStateLoss()
}

//fun Fragment.loaderShow(
//    containerId: Int,
//    prograssBarFragment: Fragment
//) {
//    childFragmentManager.startTransaction {
//        replace(containerId, prograssBarFragment)
//    }
//}

fun Fragment.loadFragment(
    containerId: Int,
    fragment:Fragment){
    childFragmentManager.startTransaction {
        replace(containerId, fragment)
    }
}

fun Fragment.removeFragment(
    prograssBarFragment: Fragment
) {
    childFragmentManager.startTransaction {
        remove(prograssBarFragment)
    }
}