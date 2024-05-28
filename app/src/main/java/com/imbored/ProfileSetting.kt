package com.imbored

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment


class ProfileSetting : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_profile_setting, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val saveBtn : Button = view.findViewById(R.id.nameSaveBtn)
        val nameEdit : EditText = view.findViewById(R.id.nameEdit)
        val avatar1 : ImageView = view.findViewById(R.id.avatar1)
        val avatar2 : ImageView = view.findViewById(R.id.avatar2)
        val avatar3 : ImageView = view.findViewById(R.id.avatar3)
        val avatar4 : ImageView = view.findViewById(R.id.avatar4)
        val avatar5 : ImageView = view.findViewById(R.id.avatar5)
        val avatar6 : ImageView = view.findViewById(R.id.avatar6)

        val sharedPreferences = requireActivity().getSharedPreferences("activity-name",Context.MODE_PRIVATE)
        var nameView : TextView = requireActivity().findViewById(R.id.greet)
        var avatarView : ImageView = requireActivity().findViewById(R.id.avatar)

        saveBtn.setOnClickListener{
            if(nameEdit.text.toString() != ""){
                sharedPreferences.edit{
                    putString("name",nameEdit.text.toString()).apply()
                }
                nameView.text = "Hi, "+nameEdit.text.toString()
                Toast.makeText(view.context,"Profile Updated",Toast.LENGTH_SHORT).show()
                nameEdit.hint = ""
            }
            else
                Toast.makeText(view.context,"Enter Your Name",Toast.LENGTH_SHORT).show()
        }

        avatar1.setOnClickListener{
            val imageId = R.drawable.avatar1
            avatarView.setImageResource(imageId)
            sharedPreferences.edit{
                putInt("avatar",imageId).apply()
            }
        }

        avatar2.setOnClickListener{
            val imageId = R.drawable.avatar2
            avatarView.setImageResource(imageId)
            sharedPreferences.edit{
                putInt("avatar",imageId).apply()
            }
        }

        avatar3.setOnClickListener{
            val imageId = R.drawable.avatar3
            avatarView.setImageResource(imageId)
            sharedPreferences.edit{
                putInt("avatar",imageId).apply()
            }
        }

        avatar4.setOnClickListener{
            val imageId = R.drawable.avatar4
            avatarView.setImageResource(imageId)
            sharedPreferences.edit{
                putInt("avatar",imageId).apply()
            }
        }

        avatar5.setOnClickListener{
            val imageId = R.drawable.avatar5
            avatarView.setImageResource(imageId)
            sharedPreferences.edit{
                putInt("avatar",imageId).apply()
            }
        }

        avatar6.setOnClickListener{
            val imageId = R.drawable.avatar6
            avatarView.setImageResource(imageId)
            sharedPreferences.edit{
                putInt("avatar",imageId).apply()
            }
        }

    }

}
