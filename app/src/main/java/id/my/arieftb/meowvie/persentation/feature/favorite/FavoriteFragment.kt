package id.my.arieftb.meowvie.persentation.feature.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.my.arieftb.meowvie.R
import id.my.arieftb.meowvie.constant.ContentType
import id.my.arieftb.meowvie.databinding.FragmentFavoriteBinding
import id.my.arieftb.meowvie.persentation.adapter.ContentRecyclerListener
import id.my.arieftb.meowvie.persentation.adapter.WatchListRecyclerAdapter
import id.my.arieftb.meowvie.persentation.base.BaseFragment
import id.my.arieftb.meowvie.utils.extension.hide
import id.my.arieftb.meowvie.utils.extension.show

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(), ContentRecyclerListener {
    private val viewModel: FavoriteViewModelImpl by viewModels()

    private lateinit var watchListAdapter: WatchListRecyclerAdapter

    override fun getViewBinding(): FragmentFavoriteBinding =
        FragmentFavoriteBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        getWatchListAll()
    }

    private fun initView() {
        watchListAdapter = WatchListRecyclerAdapter(requireContext()).apply {
            listener = this@FavoriteFragment
        }.also {
            with(binding.listFavorite) {
                layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = it
            }
        }
    }

    private fun getWatchListAll() {
        viewModel.getWatchListAll().observe(viewLifecycleOwner, {
            if (it.isNullOrEmpty()) {
                setWatchListEmpty()
            } else {
                watchListAdapter.submitList(it)
                setWatchListAvailable()
            }
        })
    }

    private fun setWatchListAvailable() {
        binding.shimmerFavoriteDefault.hide()
        binding.textFavoriteErrorMessage.hide()
        binding.listFavorite.show()
    }

    private fun setWatchListEmpty() {
        binding.shimmerFavoriteDefault.hide()
        binding.listFavorite.hide()
        binding.textFavoriteErrorMessage.apply {
            text = getString(R.string.error_message_watch_list_empty)
            show()
        }
    }

    override fun onContentClickListener(id: Long?, type: ContentType?, view: View) {
        FavoriteFragmentDirections.actionFavoriteToDetail(id ?: -1, type ?: ContentType.MOVIE)
            .also {
                view.findNavController().navigate(it)
            }
    }
}