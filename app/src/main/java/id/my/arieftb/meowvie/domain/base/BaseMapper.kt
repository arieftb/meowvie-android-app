package id.my.arieftb.meowvie.domain.base

class BaseMapper {
    interface ResponseDataMapper<R, D> {
        fun mapFromResponse(response: R?): D?
    }

    interface ResponseListDataMapper<R, D> {
        fun mapFromResponse(response: List<R>?): List<D>?
    }
}