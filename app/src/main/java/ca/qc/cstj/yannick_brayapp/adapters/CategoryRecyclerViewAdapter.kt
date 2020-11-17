package ca.qc.cstj.yannick_brayapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import ca.qc.cstj.yannick_brayapp.R
import ca.qc.cstj.yannick_brayapp.models.Category
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.viewholder_category.view.*
import java.text.NumberFormat
import java.util.*

class CategoryRecyclerViewAdapter(var categories: List<Category> = listOf()) : RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder>() {

    private lateinit var circularProgressDrawable: CircularProgressDrawable

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryRecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_category, parent, false)

        circularProgressDrawable = CircularProgressDrawable(view.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryRecyclerViewAdapter.ViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int = categories.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        //TODO: Gestion de l'interface graphique d'une carte pour une catégorie
        //Afficher son nom
        private val txvCategoryName: TextView = view.txvCategoryName


        fun bind(category: Category) {
            txvCategoryName.text = category.name


            val requestOptions = RequestOptions().placeholder(circularProgressDrawable).error(R.drawable.logo)

            view.setOnClickListener {
                Toast.makeText(it.context, category.name, Toast.LENGTH_SHORT).show()


                //val direction = CategoriesFragmentDirections.actionCategoriesFragmentToDetailCategoryFragmentWithCategory(category)

                //it.findNavController().navigate(direction)
            }

        }

    }

}