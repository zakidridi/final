package com.example.neighbors.data

import com.example.neighbors.data.service.DummyNeighborApiService
import com.example.neighbors.data.service.NeighborApiService
import com.example.neighbors.models.Neighbor

class NeighborRepository {
    private val apiService: NeighborApiService

    init {
        apiService = DummyNeighborApiService()
    }
    fun createNeighbour(Neighbor:Neighbor) =apiService.createNeighbour(Neighbor)
    fun getNeighbours(): List<Neighbor> = apiService.neighbours
    fun deleteNeighbour(Neighbor:Neighbor) = apiService.deleteNeighbour(Neighbor)

    companion object {
        private var instance: NeighborRepository? = null
        fun getInstance(): NeighborRepository {
            if (instance == null) {
                instance = NeighborRepository()
            }
            return instance!!
        }
    }
}
