package com.umc.save.Home.option


import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.google.android.material.button.MaterialButtonToggleGroup
import com.umc.save.databinding.FragmentHomeDialogBinding


class HomeDialogFragment() : DialogFragment(){

    private var _binding: FragmentHomeDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeDialogBinding.inflate(inflater, container, false)
        val view = binding.root

        initDialog()
        return view
    }

    fun initDialog() {
        binding.confirmSettingTv.text=arguments?.getString("bodyContext")
        binding.SettingDialogOkTv.text=arguments?.getString("btnOk")
        binding.SettingDialogCancelTv.text=arguments?.getString("btnCancel")
//        val btnBundle = arguments?.getString("btnData")

        //취소
        binding.SettingDialogCancelTv.setOnClickListener {
            dismiss()
        }

        //확인
        binding.SettingDialogOkTv.setOnClickListener {
            buttonClickListener.onButtonOkClicked()
            dismiss()
        }

    }

    interface onButtonClickListerner {
        fun onButtonOkClicked()
        fun onButtonNoClicked()
    }

    override fun onStart() {
        super.onStart()
//        val lp : WindowManager.LayoutParams  =  WindowManager.LayoutParams()
//        lp.copyFrom(dialog!!.window!!.attributes)
//        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        val window: Window = dialog!!.window!!
//        window.attributes =lp
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //클릭 이벤트 설정
    fun setButtonClickListener(buttonClickListener: onButtonClickListerner) {
        this.buttonClickListener = buttonClickListener
    }
    //클릭 이벤트 실행
   private lateinit var buttonClickListener: onButtonClickListerner

}


