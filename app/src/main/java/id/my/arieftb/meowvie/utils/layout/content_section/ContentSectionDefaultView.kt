package id.my.arieftb.meowvie.utils.layout.content_section

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import id.my.arieftb.meowvie.databinding.ComponentContentSectionDefaultBinding

class ContentSectionDefaultView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var binding: ComponentContentSectionDefaultBinding? = null

    init {
        binding = ComponentContentSectionDefaultBinding.inflate(LayoutInflater.from(context), null, false)
        this.addView(binding?.root)
        initAttribute(attrs)
    }

    private fun initAttribute(attrs: AttributeSet?) {
        // TODO: 6/2/21 : get attribute sectionTitle
        // TODO: 6/2/21 : get attribute marginHorizontal sectionTitle
        // TODO: 6/2/21 : get attribute layout shimmer
    }
}