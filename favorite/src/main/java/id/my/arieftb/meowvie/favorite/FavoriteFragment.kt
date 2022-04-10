package id.my.arieftb.meowvie.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.EntryPointAccessors
import id.my.arieftb.core.domain.constant.ContentType
import id.my.arieftb.meowvie.R
import id.my.arieftb.meowvie.databinding.FragmentFavoriteBinding
import id.my.arieftb.meowvie.favorite.di.DaggerFavoriteComponent
import id.my.arieftb.meowvie.favorite.di.FavoriteViewModelFactory
import id.my.arieftb.meowvie.presentation.adapter.ContentRecyclerListener
import id.my.arieftb.meowvie.presentation.adapter.WatchListRecyclerAdapter
import id.my.arieftb.meowvie.presentation.base.BaseFragment
import id.my.arieftb.meowvie.presentation.di.FavoriteModuleDependencies
import id.my.arieftb.meowvie.utils.extension.hide
import id.my.arieftb.meowvie.utils.extension.show
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(), ContentRecyclerListener {
    @Inject
    lateinit var factory: FavoriteViewModelFactory

    private val viewModel: FavoriteViewModel by viewModels {
        factory
    }

    private lateinit var watchListAdapter: WatchListRecyclerAdapter

    override fun getViewBinding(): FragmentFavoriteBinding =
        FragmentFavoriteBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder().context(requireContext()).appDependencies(
            EntryPointAccessors.fromApplication(requireContext(), FavoriteModuleDependencies::class.java)
        ).build().inject(this)
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
        lifecycleScope.launch {
            viewModel.getWatchListAll().collectLatest {
                watchListAdapter.submitData(it)
            }
        }

        watchListAdapter.addLoadStateListener {
            if (it.append.endOfPaginationReached) {
                if (watchListAdapter.itemCount < 1) {
                    setWatchListEmpty()
                } else {
                    setWatchListAvailable()
                }
            }
        }
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

    override fun onContentClickListener(id: Long?, type: ContentType?, view: View, title: String?) {
        FavoriteFragmentDirections.actionFavoriteToDetail(
            id ?: -1,
            type ?: ContentType.MOVIE,
            title
        )
            .also {
                view.findNavController().navigate(it)
            }
    }
}