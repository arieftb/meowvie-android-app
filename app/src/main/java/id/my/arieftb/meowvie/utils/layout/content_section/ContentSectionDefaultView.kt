package id.my.arieftb.meowvie.utils.layout.content_section

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import id.my.arieftb.meowvie.R
import id.my.arieftb.meowvie.databinding.ComponentContentSectionDefaultBinding

class ContentSectionDefaultView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var binding: ComponentContentSectionDefaultBinding? = null

    private var sectionTitle: String? = null
    set(value) {
        field = value
        binding?.textContentSectionTitle?.text = field
    }

    private var shimmerLayout: Int? = -1
    set(value) {
        field = value
        initShimmerLayout(field)
    }



    init {
        binding = ComponentContentSectionDefaultBinding.inflate(LayoutInflater.from(context), null, false)
        this.addView(binding?.root)

        if (!isInEditMode) {
            initAttribute(attrs)
        } else {
            initAttribute(attrs)
        }
    }

    private fun initAttribute(attrs: AttributeSet?) {
        context.obtainStyledAttributes(attrs, R.styleable.ContentSectionDefaultView).also {
            // TODO: 6/2/21 : get attribute sectionTitle
            sectionTitle = it.getString(R.styleable.ContentSectionDefaultView_section_titleText) ?: "Section Title"
            // TODO: 6/2/21 : get attribute layout shimmer
            shimmerLayout = it.getResourceId(R.styleable.ContentSectionDefaultView_section_contentShimmer, R.layout.shimmer_item_content_default)
        }.apply {
            recycle()
        }
    }

    private fun initShimmerLayout(layoutRes: Int?) {
        val inflater = LayoutInflater.from(binding?.shimmerContentSectionDefault?.context)
        inflater.inflate(layoutRes!!, binding?.shimmerContentSectionDefault)
    }
}