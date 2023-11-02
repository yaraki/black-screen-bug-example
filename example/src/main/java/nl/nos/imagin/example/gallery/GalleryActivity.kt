package nl.nos.imagin.example.gallery

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.animation.DecelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import nl.nos.imagin.example.R
import nl.nos.imagin.example.data.Repository

class GalleryActivity : AppCompatActivity() {
    private val repository = Repository()
    private val position by lazy { intent.getIntExtra(EXTRA_POSITION, -1) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_gallery)

        window.sharedElementEnterTransition.interpolator = DecelerateInterpolator(3f)
        supportPostponeEnterTransition()

        setupViewPager()
    }

    private fun setupViewPager() {
        val fragments = mutableListOf<Fragment>()

        repository.getPictures().forEach {
            fragments.add(GalleryImageFragment.newInstance(it))
        }

        (findViewById(R.id.view_pager) as? ViewPager2)?.apply {
            adapter = FragmentPagerAdapter(
                this@GalleryActivity,
                fragments
            )
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            setCurrentItem(position, false)

            post { supportStartPostponedEnterTransition() }
        }
    }

    companion object {
        private const val EXTRA_POSITION = "position"

        fun createIntent(context: Context, position: Int) =
            Intent(context, GalleryActivity::class.java).apply {
                putExtra(EXTRA_POSITION, position)
            }
    }
}