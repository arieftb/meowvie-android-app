package id.my.arieftb.meowvie.utils.layout.content_section

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import id.my.arieftb.meowvie.R
import id.my.arieftb.meowvie.databinding.ComponentContentSectionDefaultBinding
import id.my.arieftb.meowvie.presentation.model.Status
import id.my.arieftb.meowvie.utils.extension.hide
import id.my.arieftb.meowvie.utils.extension.show

class ContentSectionDefaultView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var binding: ComponentContentSectionDefaultBinding? = null

    var sectionTitle: String? = null
        set(value) {
            field = value
            binding?.textContentSectionTitle?.text = field
        }

    private var shimmerLayout: Int? = -1
        set(value) {
            field = value
            initShimmerLayout(field)
        }

    var buttonMore: ImageView? = null

    var adapter: RecyclerView.Adapter<*>? = null
        set(value) {
            field = value
            binding?.listContentSection?.adapter = field
        }

    var layoutManager: RecyclerView.LayoutManager? = null
        set(value) {
            field = value
            binding?.listContentSection?.layoutManager = field
        }

    var status: Status? = Status.LOADING
        set(value) {
            field = value
            when (field) {
                Status.LOADING -> {
                    binding?.shimmerContentSectionDefault.show()
                    binding?.listContentSection.hide()
                    binding?.textContentSectionErrorMessage.hide()
                }
                Status.ERROR -> {
                    binding?.shimmerContentSectionDefault.hide()
                    binding?.listContentSection.hide()
                    binding?.textContentSectionErrorMessage.show()
                }
                Status.SUCCESS -> {
                    binding?.shimmerContentSectionDefault.hide()
                    binding?.listContentSection.show()
                    binding?.textContentSectionErrorMessage.hide()
                }
            }
        }

    var errorMessage: String? = null
    set(value) {
        field = value
        field?.let {
            binding?.textContentSectionErrorMessage?.text = field
        }
    }

    init {
        binding =
            ComponentContentSectionDefaultBinding.inflate(LayoutInflater.from(context), null, false).also {
                this.addView(it.root)
                buttonMore = it.imageContentSectionMore
            }

        if (!isInEditMode) {
            initAttribute(attrs)
        } else {
            initAttribute(attrs)
        }
    }

    private fun initAttribute(attrs: AttributeSet?) {
        context.obtainStyledAttributes(attrs, R.styleable.ContentSectionDefaultView).also {
            sectionTitle = it.getString(R.styleable.ContentSectionDefaultView_section_titleText)
                ?: "Section Title"
            shimmerLayout = it.getResourceId(
                R.styleable.ContentSectionDefaultView_section_contentShimmer,
                R.layout.shimmer_item_content_default
            )
        }.apply {
            recycle()
        }
    }

    private fun initShimmerLayout(layoutRes: Int?) {
        val inflater = LayoutInflater.from(binding?.shimmerContentSectionDefault?.context)
        inflater.inflate(layoutRes!!, binding?.shimmerContentSectionDefault)
    }
}