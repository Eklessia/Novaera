package com.example.novaera

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.novaera.ViewModel.AppViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_second.*
import java.lang.Exception


class SecondFragment : Fragment() {

    private val myViewModel: AppViewModel by activityViewModels()
    lateinit var productSend: String
    private var  id_product=0


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        myViewModel.election.observe(viewLifecycleOwner, Observer {
            id_product=it


            myViewModel.getProductDetail(id_product).observe(viewLifecycleOwner, Observer {

                it?.let {


                    Glide.with(this).load(it.image).fitCenter().into(imagenDetalle)

                    details_name.text = it.name
                    details_desc.text=it.description
                    details_precAnterior.text=it.lastPrice.toString()
                    details_credito.text=it.credit.toString()

                    productSend=it.name
                }
            })
        })

        view.findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener { view ->
            val addresses="info@plaplix.cl"
            val subject=getString(R.string.Consulta)+" " + productSend
            val text=getString(R.string.text_mail)
            bodyMail(addresses, subject, text)

        }



        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }




    }


    fun bodyMail(addresses: String, subject: String, text: String) {
        var intent = Intent(Intent.ACTION_SEND)
        intent.data = Uri.parse("mailto:")
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(addresses))
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, text)

        try {
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "e.message", Toast.LENGTH_LONG).show()
        }

    }



}


