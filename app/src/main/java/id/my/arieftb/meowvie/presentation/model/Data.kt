package id.my.arieftb.meowvie.presentation.model

data class Data<D>(var status: Status, var data: D? =null, var errorMessage: String? = null)