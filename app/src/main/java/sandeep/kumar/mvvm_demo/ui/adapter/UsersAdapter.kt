package sandeep.kumar.mvvm_demo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_user.view.*
import sandeep.kumar.mvvm_demo.R
import sandeep.kumar.mvvm_demo.data.model.User

class UsersAdapter(val data: List<User>) : RecyclerView.Adapter<UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
    )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size
}

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: User) = with(itemView) {

        user_name_text_view.text = item.login
        Picasso.get().load(item.avatarUrl).into(user_image_view)

    }
}