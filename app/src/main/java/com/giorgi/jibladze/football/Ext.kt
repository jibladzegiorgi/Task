package com.giorgi.jibladze.football

import com.google.android.material.bottomnavigation.BottomNavigationView

fun BottomNavigationView.createBadge(menuItemId: Int) {
    this.getOrCreateBadge(menuItemId).number=50
}