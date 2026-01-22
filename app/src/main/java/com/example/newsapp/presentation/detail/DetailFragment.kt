package com.example.newsapp.presentation.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.example.newsapp.R
import com.example.newsapp.core.domain.model.News
import com.example.newsapp.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)

        // Retrieve the News object passed from HomeFragment
        @Suppress("DEPRECATION")
        val news = arguments?.getParcelable<News>("news")

        if (news != null) {
            setupUI(news)
        }
    }

    private fun setupUI(news: News) {
        var statusFavorite = news.isFavorite
        setStatusFavorite(statusFavorite)

        with(binding) {
            // Text data
            tvDetailTitle.text = news.title
            tvDetailDate.text = news.publishedDate
            tvDetailContent.text = news.description

            // Image loading with Coil
            ivDetailImage.load(news.urlToImage) {
                crossfade(true)
                placeholder(android.R.drawable.ic_menu_gallery)
                error(android.R.drawable.ic_menu_report_image)
            }

            // Read More Button (Open Browser)
            btnReadMore.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(news.url))
                startActivity(intent)
            }

            // Favorite FAB Toggle
            fabFavorite.setOnClickListener {
                statusFavorite = !statusFavorite
                viewModel.setFavoriteNews(news, statusFavorite)
                setStatusFavorite(statusFavorite)
            }

            // Back button on toolbar
            toolbar.setNavigationOnClickListener {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite)
            )
        } else {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite_border)
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}