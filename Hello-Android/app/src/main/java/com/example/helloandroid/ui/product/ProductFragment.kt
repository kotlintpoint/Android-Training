package com.example.helloandroid.ui.product

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.helloandroid.R
import com.example.helloandroid.adapter.ProductAdapter
import com.example.helloandroid.databinding.FragmentProductBinding
import com.example.helloandroid.databinding.FragmentRegisterBinding
import com.example.helloandroid.mock.productList
import com.example.helloandroid.models.Product

/**
 * A simple [Fragment] subclass.
 * Use the [ProductFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductFragment : Fragment() {

    // https://developer.android.com/develop/ui/views/layout/recyclerview
    private lateinit var binding: FragmentProductBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object: MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.drawer_dashboard, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if(menuItem.itemId == R.id.action_new){
                    val navController = activity?.findNavController(R.id.nav_host_fragment_content_drawer_dashboard)
                    navController?.navigate(R.id.action_nav_product_to_nav_new_product)
                    return true
                }
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        binding.rvProduct.layoutManager = LinearLayoutManager(context)
//        binding.rvProduct.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        binding.rvProduct.layoutManager = GridLayoutManager(context, 2)
//        binding.rvProduct.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        // for one method
//        binding.rvProduct.adapter = ProductAdapter(productList) { product -> Log.i("PRODUCT",
//            product.toString()
//        ) }

        binding.rvProduct.adapter = ProductAdapter(productList, object : ProductAdapter.OnProductClickListener {
            override fun onItemClick(product: Product) {
                Log.i("PRODUCT", product.toString())
            }

            override fun onItemEdit(product: Product) {
                Log.i("PRODUCT Edit", product.toString())
                val navController = activity?.findNavController(R.id.nav_host_fragment_content_drawer_dashboard)

                val args = Bundle()
                args.putInt(SCORE, 20)
                args.putString(USER, "Tops")

                navController?.navigate(R.id.action_nav_product_to_nav_new_product, args)
            }

            override fun onItemDelete(product: Product) {
                Log.i("PRODUCT Delete", product.toString())
//                productList.remove(product)
                showConfirmDialog(product)
                Log.i("PRODUCT Delete", productList.toString())

                // refresh adapter when ever data set changed either add, update, delete
                // to refresh adapter use notifyDataSetChanged method
//                binding.rvProduct.adapter?.notifyDataSetChanged()
            }
        })

    }

    fun showConfirmDialog(product: Product) {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete Product")
            .setMessage("Are you sure want to Delete this Product ?")
            .setPositiveButton("Yes") { dialog, _ ->
                productList.remove(product)
                binding.rvProduct.adapter?.notifyDataSetChanged()
            }
            .setNegativeButton("No") { dialog, _ ->  dialog.dismiss() }
            .setNeutralButton("Cancel") { dialog, _ ->  dialog.dismiss() }
            .create()
            .show()
    }

}