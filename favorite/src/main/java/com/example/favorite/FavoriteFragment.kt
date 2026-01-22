package com.example.newsapp.favorite

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.newsapp.core.di.FavoriteModuleDependencies
import com.example.newsapp.core.ui.NewsAdapter
import com.example.newsapp.favorite.databinding.FragmentFavoriteBinding
import com.example.newsapp.favorite.di.DaggerFavoriteComponent
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: FavoriteViewModel by viewModels { factory }

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        DaggerFavoriteComponent.builder()
            .context(context)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    context.applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)

        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavoriteBinding.bind(view)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val newsAdapter = NewsAdapter { news ->
            val bundle = Bundle().apply {
                putParcelable("news", news)
            }

            // Dynamic ID lookup to safely navigate from DFM to :app module
            val actionId = resources.getIdentifier(
                "action_favorite_to_detail",
                "id",
                requireContext().packageName
            )

            if (actionId != 0) {
                findNavController().navigate(actionId, bundle)
            }
        }

        // Reactive observation of the favorite list
        viewModel.favoriteNews.observe(viewLifecycleOwner) { data ->
            newsAdapter.submitList(data)
            binding.tvEmpty.visibility = if (data.isNotEmpty()) View.GONE else View.VISIBLE
        }

        with(binding.rvFavorite) {
            setHasFixedSize(true)
            adapter = newsAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}