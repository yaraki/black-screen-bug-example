package nl.nos.imagin.example.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import nl.nos.imagin.example.R
import nl.nos.imagin.example.AssetLoader
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

        loadImage(data, view.findViewById(R.id.image_view))
    }

    private fun loadImage(picture: Picture, imageView: ImageView) {
        imageView.setImageDrawable(
            assetLoader.createDrawableFromAsset(
                imageView.resources,
                picture.fileName
            )
        )

        enableImageZoomControls(picture, imageView)
    }

    private fun enableImageZoomControls(picture: Picture, imageView: ImageView) {
        view?.let {
            imageView.transitionName = picture.name
            imageView.tag = picture.name
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
