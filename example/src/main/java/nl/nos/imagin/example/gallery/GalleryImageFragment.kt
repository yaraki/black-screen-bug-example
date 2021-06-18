package nl.nos.imagin.example.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.gallery_item.*
import nl.nos.imagin.example.AssetLoader
import nl.nos.imagin.example.R
import nl.nos.imagin.example.data.Picture

class GalleryImageFragment : Fragment() {
    private val assetLoader = AssetLoader()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.gallery_item, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = arguments?.getParcelable(IMAGE_DATA) as? Picture ?: return

        loadImage(data)
    }

    private fun loadImage(picture: Picture) {
        image_view.setImageDrawable(
            assetLoader.createDrawableFromAsset(
                image_view.resources,
                picture.fileName
            )
        )

        enableImageZoomControls(picture)
    }

    private fun enableImageZoomControls(picture: Picture) {
        view?.let {
            image_view.transitionName = picture.name
            image_view.tag = picture.name
        }
    }

    companion object {
        const val IMAGE_DATA= "IMAGE_DATA"

        fun newInstance(image: Picture?): Fragment {
            val args = Bundle()
            args.putParcelable(IMAGE_DATA, image)
            val imageFragment =
                GalleryImageFragment()
            imageFragment.arguments = args
            return imageFragment
        }
    }
}
