package com.dominic.sherlarapp

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dominic.sherlarapp.Base.Revice
import com.dominic.sherlarapp.Models.MyRecycleView
import com.dominic.sherlarapp.Models.Poem
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.item_bottomsheet.*
import kotlinx.android.synthetic.main.item_bottomsheet.view.*
import java.util.function.UnaryOperator


class ListFragment : Fragment() {
    lateinit var root:View
    lateinit var list:ArrayList<Poem>
    var numberLikes:Int = 0
    lateinit var lovedList:ArrayList<Poem>
    var likeCollection = Likes._LikeCollection
    lateinit var myRecycleView: MyRecycleView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        list = ArrayList()
        lovedList = ArrayList()
        likeCollection = Likes._LikeCollection
        root = inflater.inflate(R.layout.fragment_list, container, false)
        root.img_back.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }

       // loadData()
        scanMe()


        myRecycleView = MyRecycleView(list,object : MyRecycleView.Click{
            override fun onClick(poem: Poem) {
                numberLikes = Likes.likes!!
                val bottomSheetDialog = BottomSheetDialog(requireContext())
                val sobject = layoutInflater.inflate(R.layout.item_bottomsheet,null,false)
                sobject.bottomshett_poemname.text = poem._poemName
                sobject.bottomsheet_poeminfo.text = poem._poemInfo
                if (poem._like){
                    sobject.selected.setImageResource(R.drawable.saralanganlar_2)
                }
                bottomSheetDialog.setContentView(sobject)
                bottomSheetDialog.btn_addloved.setOnClickListener {
                  if (poem._like == false){
                      poem._like = true
                      sobject.selected.setImageResource(R.drawable.saralanganlar_2)
                      lovedList.add(poem)
                      numberLikes += 1
                      Likes.likes = numberLikes
                      Revice.linkedList.addAll(lovedList)
                      bottomSheetDialog.cancel()
                      Toast.makeText(requireContext(), "Added to liked", Toast.LENGTH_SHORT).show()
                  }else if(poem._like == true){
                      poem._like = false
                      sobject.selected.setImageResource(R.drawable.selected)
                      lovedList.remove(poem)
                      numberLikes -= 1
                      Likes.likes = numberLikes
                      Revice.linkedList.addAll(lovedList)
                      bottomSheetDialog.cancel()
                      Toast.makeText(requireContext(), "Removed from liked", Toast.LENGTH_SHORT).show()
                  }
                }
                bottomSheetDialog.btn_copy.setOnClickListener {
                    Toast.makeText(requireContext(), "Copied!", Toast.LENGTH_SHORT).show()
                }
                bottomSheetDialog.btn_share.setOnClickListener {
                    Toast.makeText(requireContext(), "it hasn`t finoshed yet", Toast.LENGTH_SHORT).show()
                }
                
                bottomSheetDialog.show()
            }
        })
        root.myrecycleview.adapter = myRecycleView
        /*
          if (like==false){
                        Toast.makeText(requireContext(), "Added!", Toast.LENGTH_SHORT).show()
                        sobject.selected.setImageResource(R.drawable.saralanganlar_2)
                        lovedList.add(poem)
                        numberLikes += 1
                        Likes.likes = numberLikes
                        like = true
                    }else if(like==true){
                        Toast.makeText(requireContext(), "Removed!", Toast.LENGTH_SHORT).show()
                        sobject.selected.setImageResource(R.drawable.selected)
                        numberLikes -= 1
                        Likes.likes = numberLikes
                        lovedList.remove(poem)
                        like = false

                    }
         */

        return root
    }
    private fun loadData(){
        list = ArrayList()
        list.add(Poem("3 So`z","Shunchaki 3 so`z yashashga undaydi,\nSEN MENGA KERAKSAN\nShunchaki 3 so`z ortga qaytaradi\nMEN SENI SOG`INDIM\nShunchaki 3 so`z borki ko`zni yoshlatadi\nMEN O`ZGANI SEVAMAN\nShunchaki 3 so`z esa bahtli qiladi\nDOIM BIRGA BO`LAMIZ\nYurakni larzaga keltiruvchi 3 so`z esa\nMEN SENI SEVAMAN",false))
        list.add(Poem("Sev deyolmayman","Sevaman deymanu sev deyolmayman,\nMajburiy sevgini tan ololmayman\nAgar meni emas o`zgani sevsang\nBaxtli bo`l deymanu unutolmayman",false))
        list.add(Poem("Sevgi","Sevgi bu bahor,\nUni qilma hor,\nO`tib ketganda,\nUnga bo`lma zor.",false))
        list.add(Poem("Ishonma","Agar nomard so`z besa, So`zlariga ishonma, Ko`p tikilib qarasa, Ko`zlariga ishonma.",false))
        list.add(Poem("Do`st","Do`stni eskisi yaxshi, \nSMS ni yangisi",false))
        list.add(Poem("Do`st bilan","Do`st bilan obod uying \nGar bo`lsa ham vayrona ham\nDo`st qadam qo`ymas ekan\nGar gayroni koshonaham",false))
        list.add(Poem("Sevdim","Sizni sevdim bo`shlik qilib,\nTez unutdim yoshlik qilib,\nSiz yuribsiz aza qilib,\nMan yuribman mazza qilib",false))
        list.add(Poem("O`qimoqda!","Hozirda dunyoda 30 mingga yaqin aqli zaif insonlar bo`lib,\n\t27 mingtasi jinnixonada, 2 mingtasi oila davrasida,\n999 tasi ko`chada sang`ib yuribdi, 1 tasi SMS o`qimoqda!",false))
        Likes.second = true
        Likes._LikeCollection.addAll(list)

    }
    private fun scanMe(){
        if (Likes.second == true){
            list.addAll(Likes._LikeCollection)
        }else{
            loadData()
        }
    }
}
