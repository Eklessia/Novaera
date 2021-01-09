package com.example.novaera

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.novaera.model.api.Local.Product
import kotlinx.android.synthetic.main.item_products.view.*

class AdapterProducts(val callback: dataProduct):RecyclerView.Adapter<AdapterProducts.ProductViewHolder>() {



        private var listProducts=  emptyList<Product>()

        fun updateAdapter(list:List<Product>){
            listProducts=list;
            notifyDataSetChanged()
        }


        // View Holder

        inner class ProductViewHolder(itemVista: View): RecyclerView.ViewHolder (itemVista){

            val itemImage: ImageView =itemVista.foto_product
            val itemPrice: TextView =itemVista.valor_products
            val itemTitle: TextView =itemVista.title_products

            val click= itemVista.setOnClickListener {
                callback.passItem(listProducts[adapterPosition])

            }
        }

    interface  dataProduct{
        fun passItem(product:Product)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_products, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        holder.itemTitle.text=listProducts[position].name
        holder.itemPrice.text=listProducts[position].price.toString()

        val image=listProducts[position].image
        Glide.with(holder.itemView.context).load(image)
                                            .fitCenter()
                                            .transform(RoundedCorners(10))
                                            .into(holder.itemImage)
    }

    override fun getItemCount()= listProducts.size


}