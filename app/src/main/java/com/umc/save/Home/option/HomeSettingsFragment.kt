package com.umc.save.Home.option



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.umc.save.MainActivity
import com.umc.save.R
import com.umc.save.databinding.FragmentHomeBinding
import com.umc.save.databinding.FragmentHomeSettingsBinding


class HomeSettingsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private lateinit var binding: FragmentHomeSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentHomeSettingsBinding.inflate(layoutInflater)

        //프로필 수정 버튼 클릭
        binding.settingsProfileEditBtn.setOnClickListener {
            changeFragment(HomeEditProfileFragment())
        }

        //로그아웃 버튼 클릭
        binding.settingsLogoutBtn.setOnClickListener {
            ClickViewEvents(0)
        }

        //계정탈퇴 텍스트 클릭
        binding.settingDropOutTv.setOnClickListener {
            ClickViewEvents(1)
        }
        //게정탈퇴 이미지 클릭
        binding.settingDropOutIv.setOnClickListener{
            ClickViewEvents(1)
        }

        return binding.root
    }

    //뷰 클릭 이벤트 정의
    private fun ClickViewEvents(id: Int) {

        val dialog = HomeDialogFragment()

        when (id) {
            0 -> {
                /*val logoutbtn = arrayOf("취소", "확인")*/
                dialog.arguments = bundleOf(
                    "bodyContext" to "로그아웃 하시겠습니까?",
                    "btnOk" to "확인",
                    "btnCancel" to "취소"
                )
                dialog.setButtonClickListener(object: HomeDialogFragment.onButtonClickListerner {
                    override fun onButtonNoClicked() {
                        TODO("Not yet implemented")
                    }
                    override fun onButtonOkClicked() {
                        TODO("Not yet implemented")
                    }
                })
                dialog.show(this.childFragmentManager, "HomeDialog")
            }

            1 -> {
                /*val signutbtn = arrayOf("취소", "탈퇴")*/
                dialog.arguments = bundleOf(
                    "bodyContext" to "정말로 SAVE에서 탈퇴하시겠습니까? \n현재까지 기록된 내용들은 모두 사라집니다.",
                    "btnOk" to "탈퇴",
                    "btnCancel" to "취소"
                )
                dialog.setButtonClickListener(object: HomeDialogFragment.onButtonClickListerner {
                    override fun onButtonNoClicked() {
                        TODO("Not yet implemented")
                    }
                    override fun onButtonOkClicked() {
                        TODO("Not yet implemented")
                    }
                })
                dialog.show(this.childFragmentManager, "HomeDialog")
            }
        }

    }

    //fragment to fragment method
    private fun changeFragment(fragment: Fragment) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, fragment)
            .disallowAddToBackStack()
            .commit()
    }
}

