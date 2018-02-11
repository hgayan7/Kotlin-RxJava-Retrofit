package com.example.him.rxjava_kotlin_retrofit

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.him.rxjava_kotlin_retrofit.Model.UserData
import com.example.him.rxjava_kotlin_retrofit.Rest.ApiClient
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_data.view.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var mAndroidArrayList: ArrayList<UserData>? = null
    private var client=ApiClient()
    private var adapter: UserAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        client.loadUserData().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse)
        recyclerView!!.layoutManager=LinearLayoutManager(this)

    }

    private fun handleResponse(list: List<UserData>) {

        mAndroidArrayList = ArrayList(list)
        adapter = UserAdapter(mAndroidArrayList!!, this)
        //setting the adpater
        recyclerView.adapter = adapter
    }

    //adapter for recyclerview
    private class UserAdapter(items:List<UserData>,context: Context):RecyclerView.Adapter<UserAdapter.MyViewholder>(){

        var item=items
        var c=context
        override fun onBindViewHolder(holder: MyViewholder?, position: Int) {

            holder?.bind(item[position],position)
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewholder {

            return UserAdapter.MyViewholder(LayoutInflater.from(c).inflate(R.layout.row_data,parent,false))

        }

        override fun getItemCount(): Int {

            return item.size

        }

        class MyViewholder(view : View):RecyclerView.ViewHolder(view){

            fun bind(android: UserData, position: Int) {

                itemView.name.text = android.login
                itemView.accId.text = android.id.toString()
                Picasso.with(itemView.context).load(android.avatarUrl)
                        .into(itemView.imageView)

            }
        }
    }

}
