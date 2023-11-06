package com.example.hoysecome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hoysecome.databinding.FragmentIngredientListBinding
import com.example.hoysecome.interfaces.apiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val arg_name = "name";
class ingredient_list : Fragment() {
    private var name: String? = "apple";

    private var _binding:FragmentIngredientListBinding?=null;
    private val binding get()=_binding!!;
    private lateinit var ingredientesRvadapter: Ingredientes_RVAdapter;
    private lateinit var ingredientesList:MutableList<String>;

    private var instance: ingredient_list? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this;
        arguments?.let {
            name = it.getString(arg_name)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentIngredientListBinding.inflate(layoutInflater);
        ingredientesList=mutableListOf();
        initRecycler();
        insertIngredients();
        return binding.root;
    }

    companion object {

        @JvmStatic
        fun newInstance(name: String) =
                ingredient_list().apply {
                    arguments = Bundle().apply {
                        putString(arg_name, name);
                    }
                }
        fun create(): ingredient_list = ingredient_list()
    }

    private fun initRecycler(){
        ingredientesRvadapter = Ingredientes_RVAdapter(ingredientesList)
        binding.ingredientesList.layoutManager = LinearLayoutManager(this.context)
        binding.ingredientesList.adapter = ingredientesRvadapter
    }

    private fun getRetrofit(base_url_resource:Int): Retrofit {
        return Retrofit.Builder()
                .baseUrl(resources.getString(base_url_resource))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
    fun insertIngredients(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = getRetrofit(R.string.api_ingredients).create(apiService::class.java).consultIngredient("search?apiKey=9a1052eefaea4ef4a60c86e20847c051&query=" + name)
            val ingredients = response.body()
            activity?.runOnUiThread{
                if(response.isSuccessful){
                    if(ingredients?.results?.size() != 0){
                        ingredientesList.clear()
                        for (key in ingredients!!.results!!.asJsonArray){
                            val art:String = key.asJsonObject.get("name").asString
                            ingredientesList.add(art)
                        }
                    }else{
                        ingredientesList.clear()
                    }
                    ingredientesRvadapter.notifyDataSetChanged()

                }else{
                    ingredientesList.clear()
                    ingredientesRvadapter.notifyDataSetChanged()
                }
            }
        }
    }
}