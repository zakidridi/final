package com.example.neighbors.fragments
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.IntentSenderRequest
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.neighbors.MainActivity
import com.example.neighbors.NavigationListener
import com.example.neighbors.R
import com.example.neighbors.adapters.ListNeighborsAdapter
import com.example.neighbors.data.NeighborRepository
import com.example.neighbors.models.Neighbor
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.neighbors.adapters.ListNeighborsAdapter.ListNeighborHandler as ListNeighborHandler
class ListNeighborsFragment:Fragment() {
    /**
     * Fonction permettant de définir une vue à attacher à un fragment
     */
    @SuppressLint("last")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.list_neighbors_fragment, container, false)
            (activity as? NavigationListener)?.let {
                it.updateTitle(R.string.list)
        }
        recyclerView = view.findViewById(R.id.neighbors_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        var addNeighbor=view.findViewById<FloatingActionButton>(R.id.add_neighbor)
        addNeighbor.setOnClickListener {
            (activity as? NavigationListener)?.let {
                it.showFragment(AddNeighbourFragment())
            }
        }
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val neighbors = NeighborRepository.getInstance().getNeighbours()
        val adapter = ListNeighborsAdapter(neighbors,this)
        recyclerView.adapter = adapter

    }
    private lateinit var recyclerView: RecyclerView
    fun onDeleteNeibor(neighbor: Neighbor) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle("Neighbor Alert")
        alertDialogBuilder.setMessage("Voulez-vous supprimer ${neighbor.name} ?")
        alertDialogBuilder.setPositiveButton(android.R.string.yes) { dialog, which ->
            Toast.makeText(context,
                android.R.string.yes, Toast.LENGTH_SHORT).show()
            NeighborRepository.getInstance().deleteNeighbour(neighbor)
            //on refresh
            val neighbors = NeighborRepository.getInstance().getNeighbours()
            val adapter = ListNeighborsAdapter(neighbors, this)
            recyclerView.adapter = adapter
        }

        alertDialogBuilder.setNegativeButton(android.R.string.no) { dialog, which ->
            Toast.makeText(context,
                android.R.string.no, Toast.LENGTH_SHORT).show()
        }
        alertDialogBuilder.show()
    }
}
