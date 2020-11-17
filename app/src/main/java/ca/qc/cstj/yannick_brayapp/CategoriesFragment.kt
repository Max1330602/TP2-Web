package ca.qc.cstj.yannick_brayapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import ca.qc.cstj.yannick_brayapp.adapters.CategoryRecyclerViewAdapter
import ca.qc.cstj.yannick_brayapp.helpers.RepositoryResult
import ca.qc.cstj.yannick_brayapp.models.Category
import ca.qc.cstj.yannick_brayapp.repositories.CategoryRepository
import ca.qc.cstj.yannick_brayapp.helpers.TopSpacingItemDecoration
import kotlinx.android.synthetic.main.activity_categories.*
import kotlinx.coroutines.launch
import kotlin.random.Random

class CategoriesFragment : Fragment() {

    private lateinit var categoryRecyclerViewAdapter : CategoryRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val topSpacingItemDecoration = TopSpacingItemDecoration(30)

        //TODO: Afficher les planètes dans le Recycler View
        categoryRecyclerViewAdapter = CategoryRecyclerViewAdapter()

        rcvCategories.apply {
            //layoutManager = GridLayoutManager(this.context,2)
            layoutManager = LinearLayoutManager(this.context)
            adapter = categoryRecyclerViewAdapter
            addItemDecoration(topSpacingItemDecoration)
        }

        lifecycleScope.launch {

            when(val result = CategoryRepository.getCategories()) {
                is RepositoryResult.Success -> {
                    categoryRecyclerViewAdapter.categories = result.data
                    rcvCategories.adapter!!.notifyDataSetChanged()
                }
                is RepositoryResult.Error -> {
                    Toast.makeText(this@CategoriesFragment.context, result.exception.message, Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    companion object {
        fun newInstance() =
            CategoriesFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}