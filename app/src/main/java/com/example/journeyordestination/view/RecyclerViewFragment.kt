package com.example.journeyordestination.view


import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ImageSpan
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
import com.example.journeyordestination.BuildConfig
import com.example.journeyordestination.R
import com.example.journeyordestination.databinding.FragmentRecyclerViewBinding
import com.example.journeyordestination.viewmodel.DirectionsViewModel
import com.seatgeek.placesautocomplete.PlacesAutocompleteTextView


const val TAG1 = "fragment"

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
        //setting recyclerview adapter and passing in removeAt functionality
        destinationAdapter = DestinationAdapter { position ->
            viewModel.remove(position)
        }
        headerAdapter = HeaderAdapter(viewModel)
        chooseRVLayout()
        observeViewModel()

        //spannable for how to use app directions
        val tv: TextView = view.findViewById(R.id.how_to_use_app_text_view)
        setSpannable(tv)
    }

    private fun chooseRVLayout() {
        recyclerView = binding.recyclerViewDestinations
        recyclerView.layoutManager = LinearLayoutManager(context)
        //linking both adapters
        val concatAdapter = ConcatAdapter(headerAdapter, destinationAdapter)
        recyclerView.adapter = concatAdapter
    }

    private fun observeViewModel() {

        viewModel.destinationApiResponse.observe(viewLifecycleOwner) { duration ->
            duration?.let {
                //update rv list with ListAdapter "submitList" function
                destinationAdapter.submitList(duration)
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            isLoading.let {
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
        val iconCar: Drawable? = resources.getDrawable(R.drawable.ic_go_to_destination)
        val iconCheck: Drawable? = resources.getDrawable(R.drawable.ic_complete_entry)
        val iconSwap: Drawable? = resources.getDrawable(R.drawable.ic_swap_directions)

        val string =
            SpannableString("1. Open Navigation  \n\n2. Choose Location\n    & Destination \n\n3. Swap if needed  \n\n4. Add to list  ")
        iconCar?.setBounds(0, 0, iconCar.intrinsicWidth, iconCar.intrinsicHeight)
        iconCheck?.setBounds(0, 0, iconCheck.intrinsicWidth, iconCheck.intrinsicHeight)
        iconSwap?.setBounds(0, 0, iconSwap.intrinsicWidth, iconSwap.intrinsicHeight)

        val imgCar = iconCar?.let { ImageSpan(it, ImageSpan.ALIGN_CENTER) }
        string.setSpan(imgCar, 19, 21, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        val imgSwap = iconSwap?.let { ImageSpan(it, ImageSpan.ALIGN_CENTER) }
        string.setSpan(imgSwap, 79,  80, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        val imgCheck = iconCheck?.let { ImageSpan(it, ImageSpan.ALIGN_CENTER) }
        string.setSpan(imgCheck, 97, 98, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        tv.text = string
    }
}
