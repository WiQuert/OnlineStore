package com.wiquert.onlinestore.screens.home.model

import com.wiquert.onlinestore.R


data class Advantage(
    val icon: Int,
    val title: String,
    val description: String
)

val advantagesList = listOf(
    Advantage(R.drawable.main_advantage_24, "Круглосуточная поддержка", "Мы всегда на связи – 24 на 7 помогаем решать любые вопросы"),
    Advantage(R.drawable.main_advantage_delivery, "Бесплатная доставка", "Бесплатно доставим ваш заказ при покупке от 800 рублей"),
    Advantage(R.drawable.main_advantage_geo, "Большое количество магазинов", "Наша сеть магазинов насчитывает свыше 1200 штук"),
    Advantage(R.drawable.main_advantage_original, "Только оригинальные бренды", "Все наши товары проходят тщательную проверку на оригинальность"),
    Advantage(R.drawable.main_advantage_sales, "Отличные цены", "Держим цены на достойном уровне и постоянно предлагаем скидки"),
    Advantage(R.drawable.main_advantage_love, "Любим своих покупателей", "Ориентация в первую очередь на вас, наши дорогие клиенты!"),
)
