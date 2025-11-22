package br.coffein.server.coffein.mapper

interface Mapper<T, U> {

    fun map(t: T): U

}