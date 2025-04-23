package `in`.kaligotla.bitcode_android_assignment9

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import `in`.kaligotla.bitcode_android_assignment9.databinding.FragmentProductDetailBinding

class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!
    private val cartViewModel: CartViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val product = arguments?.getParcelable<Product>("product") ?: return

        binding.textViewDetailTitle.text = product.title
        binding.textViewDetailDescription.text = product.description
        binding.textViewDetailPrice.text = "₹${product.price}"

        var quantity = 1
        binding.textViewQuantitySelected.text = quantity.toString()

        binding.buttonPlusQty.setOnClickListener {
            quantity++
            binding.textViewQuantitySelected.text = quantity.toString()
        }

        binding.buttonMinusQty.setOnClickListener {
            if (quantity > 1) {
                quantity--
                binding.textViewQuantitySelected.text = quantity.toString()
            }
        }

        binding.buttonAddToCart.setOnClickListener {
            cartViewModel.addToCart(product, quantity)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}