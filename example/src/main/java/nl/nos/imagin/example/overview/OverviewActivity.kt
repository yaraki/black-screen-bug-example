package nl.nos.imagin.example.overview

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_overview.*
import nl.nos.imagin.example.R
import nl.nos.imagin.example.data.Picture
import nl.nos.imagin.example.data.Repository
import nl.nos.imagin.example.gallery.GalleryActivity

class OverviewActivity : AppCompatActivity(),
    OverviewAdapter.OnPictureClickedListener {

    private val repository = Repository()
    private val adapter = OverviewAdapter().apply {
        onPictureClickedListener = this@OverviewActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_overview)

        adapter.pictures.addAll(repository.getPictures())

        recycler_view.layoutManager = GridLayoutManager(this, 2)
        recycler_view.adapter = adapter
    }

    override fun onPictureClicked(view: View, picture: Picture, position: Int) {
        startActivity(
            GalleryActivity.createIntent(this, position),
            ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                view,
                view.transitionName
            ).toBundle()
        )
    }
}
