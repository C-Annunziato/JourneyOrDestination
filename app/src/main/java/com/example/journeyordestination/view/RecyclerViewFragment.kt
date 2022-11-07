package com.example.journeyordestination.view


import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ImageSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.journeyordestination.R
import com.example.journeyordestination.databinding.FragmentRecyclerViewBinding
import com.example.journeyordestination.viewmodel.DirectionsViewModel


const val hey = "fragment"

class RecyclerViewFragment : Fragment() {

    private val viewModel: DirectionsViewModel by viewModels()
    private lateinit var headerAdapter: HeaderAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentRecyclerViewBinding
    private lateinit var destinationAdapter: DestinationAdapter
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        destinationAdapter = DestinationAdapter { position ->
            viewModel.remove(position)
        }
        headerAdapter = HeaderAdapter(viewModel)
        chooseRVLayout()
        observeViewModel()

        val tv: TextView = view.findViewById(R.id.how_to_use_app_text_view)
        setSpannable(tv)
    }

    private fun chooseRVLayout() {
        recyclerView = binding.recyclerViewDestinations
        recyclerView.layoutManager = LinearLayoutManager(context)
        val concatAdapter = ConcatAdapter(headerAdapter, destinationAdapter)
        recyclerView.adapter = concatAdapter
    }

    private fun observeViewModel() {

        viewModel.destinationApiResponse.observe(viewLifecycleOwner) { duration ->
            Log.i(hey, "From: Fragment ->OUTSIDE DURATION")
            duration?.let {
                Log.i(hey, "From: Fragment -> observeViewModel destinationapiresponse called")

                destinationAdapter.submitList(duration)
                headerAdapter.updateListSize(duration.size)
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            isLoading.let {
                Log.i(TAG, "From: Fragment -> loading view is being set to GONE")
                binding.loadingView.visibility = if (it) View.VISIBLE else View.GONE
                binding.listError.visibility = View.GONE
                binding.howToUseAppTextView.visibility = View.GONE
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            error.let {
                binding.listError.text = viewModel.error.value.toString()
                binding.listError.visibility = View.VISIBLE
                binding.howToUseAppTextView.visibility = View.GONE
            }
        }

        viewModel.howTo.observe(viewLifecycleOwner) { howToUseAppTv ->
            howToUseAppTv.let {
                binding.howToUseAppTextView.visibility = if (it) View.VISIBLE else View.GONE
            }
        }
    }

    private fun setSpannable(tv: TextView) {
        val icon: Drawable? = resources.getDrawable(R.drawable.ic_go_to_destination)
        val string = SpannableString("1. Click the  \n 2. Then do this \n 3. Then do this")
        icon?.setBounds(0, 0, icon.intrinsicWidth, icon.intrinsicHeight)
       val img = icon?.let { ImageSpan(it, ImageSpan.ALIGN_CENTER) }
        string.setSpan(
            img,
           13,
         14,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
            tv.text = string


    }


}
