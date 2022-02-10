package com.dominic.sherlarapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dominic.sherlarapp.Base.Revice
import com.dominic.sherlarapp.Models.MyRecycleView
import com.dominic.sherlarapp.Models.Poem
import kotlinx.android.synthetic.main.fragment_about.*
import kotlinx.android.synthetic.main.fragment_about.view.*


class AboutFragment : Fragment() {
    lateinit var root:View
    lateinit var type:String
    lateinit var Mylist:ArrayList<Poem>
    lateinit var myRecycleView: MyRecycleView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_about, container, false)
        type = Revice.type
        Mylist = ArrayList()
        if (type == "like"){
            root.img_type.setImageResource(R.drawable.saralanganlar_2)
            root.text_type.setText("Saralangan She`rlar")
            Mylist.addAll(Revice.linkedList)
            myRecycleView = MyRecycleView(Mylist,object :MyRecycleView.Click{
                override fun onClick(poem: Poem) {

                }
            })


        }else if(type == "news"){
            data()
            root.text_type.setText("Yangi She`rlar ")
            root.img_type.setImageResource(R.drawable.yangilar)
            myRecycleView = MyRecycleView(Mylist,object : MyRecycleView.Click{
                override fun onClick(poem: Poem) {

                }
            })


        }else{
            // nothing now...
        }


        root.recycle_type.adapter = myRecycleView

        return root
    }
    private fun data(){
        Mylist.add(Poem("Cheksang!","Cheksang PALL MALL\nO`lasan ALLAMAHAL",false))
        Mylist.add(Poem("Ko`pligidan","Shaftoli bo`yi pas mevasi ko`pligidan,\nQizlarni esa pas sevgani ko`pligidan",false))
        Mylist.add(Poem("Bilaman","Bilaman bilganingni,\nBilmaysan bilganimni,\nBilganingda bilganimni,\nBilarding bitlaganingni",false))
        Mylist.add(Poem("Ta`omlar qoli","Ta`omlar qolib,\nDardlar bilan oziqlanyapmiz..:)",false))
    }
}