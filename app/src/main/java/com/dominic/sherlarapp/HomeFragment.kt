package com.dominic.sherlarapp

import android.app.ActionBar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import com.dominic.sherlarapp.Base.Revice
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {
    lateinit var root:View
    lateinit var animation:Animation
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_home, container, false)

        root.cardview_sevgisher.setOnClickListener {
            anim(root.cardview_sevgisher)
            findNavController().navigate(R.id.listFragment)
        }
        root.cardview_sogincharmon.setOnClickListener {

            anim(root.cardview_sogincharmon)
            findNavController().navigate(R.id.listFragment)
        }
        root.cardview_tabrik.setOnClickListener {

            anim(root.cardview_tabrik)
            findNavController().navigate(R.id.listFragment)
        }
        root.cardview_otaona.setOnClickListener {

            anim(root.cardview_otaona)
            findNavController().navigate(R.id.listFragment)
        }
        root.cardview_dostlik.setOnClickListener {

            anim(root.cardview_dostlik)
            findNavController().navigate(R.id.listFragment)
        }
        root.cardview_hazil.setOnClickListener {

            anim(root.cardview_hazil)
            findNavController().navigate(R.id.listFragment)
        }
        root.cardview_2.setOnClickListener {
            anim(cardview_2)
            Revice.type = "like"
            findNavController().navigate(R.id.aboutFragment)
        }
        root.cardview_1.setOnClickListener {
            anim(cardview_1)
            Revice.type = "news"
            findNavController().navigate(R.id.aboutFragment)
        }
        root.text_saralanganlar_soni.text = Likes.likes.toString()



        return root
    }
    private fun anim(card:CardView){
        val MeAnim = AnimationUtils.loadAnimation(requireContext(),R.anim.anim_click)
        card.startAnimation(MeAnim)
    }

}