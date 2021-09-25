package com.company.metrix.services.awards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.metrix.BackButtonHandler
import com.company.metrix.databinding.FragmentAwardsBinding
import com.company.metrix.databinding.FragmentMainBinding
import com.company.metrix.model.AwardInfo
import com.company.metrix.model.AwardNomination
import com.company.metrix.model.AwardType

class FragmentAwards : Fragment(), BackButtonHandler {

    private var binding : FragmentAwardsBinding? = null
    private var awardsAdapter : AwardsListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAwardsBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupOnBackButtonPressed()
        setupAwards()
    }

    override fun setupOnBackButtonPressed() {
        binding?.backButton?.setOnClickListener{
            activity?.onBackPressed()
        }
    }

    private fun setupAwards(){
        awardsAdapter = AwardsListAdapter()
        binding?.awardsList?.apply{
            adapter = awardsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        setupDummyAwards()
    }

    private fun setupDummyAwards() {
        awardsAdapter?.submitList(
            listOf(
                AwardInfo(
                    "Лучший в команде",
                    nominations = listOf(
                        AwardNomination(
                            type = AwardType.CUP,
                            isReceived = true
                        ),
                        AwardNomination(
                            type = AwardType.FIRST_PLACE,
                            isReceived = false
                        ),
                        AwardNomination(
                            type = AwardType.SECOND_PLACE,
                            isReceived = false
                        ),
                        AwardNomination(
                            type = AwardType.THIRD_PLACE,
                            isReceived = true
                        )
                    )
                ),
                AwardInfo(
                    "Самый быстрый",
                    nominations = listOf(
                        AwardNomination(
                            type = AwardType.CUP,
                            isReceived = false
                        ),
                        AwardNomination(
                            type = AwardType.FIRST_PLACE,
                            isReceived = true
                        ),
                        AwardNomination(
                            type = AwardType.SECOND_PLACE,
                            isReceived = false
                        ),
                        AwardNomination(
                            type = AwardType.THIRD_PLACE,
                            isReceived = true
                        )
                    )
                ),
                AwardInfo(
                    "Сама вежливость",
                    nominations = listOf(
                        AwardNomination(
                            type = AwardType.CUP,
                            isReceived = false
                        ),
                        AwardNomination(
                            type = AwardType.FIRST_PLACE,
                            isReceived = false
                        ),
                        AwardNomination(
                            type = AwardType.SECOND_PLACE,
                            isReceived = true
                        ),
                        AwardNomination(
                            type = AwardType.THIRD_PLACE,
                            isReceived = true
                        )
                    )
                ),
                AwardInfo(
                    "Наиболее продуктивный",
                    nominations = listOf(
                        AwardNomination(
                            type = AwardType.CUP,
                            isReceived = true
                        ),
                        AwardNomination(
                            type = AwardType.FIRST_PLACE,
                            isReceived = false
                        ),
                        AwardNomination(
                            type = AwardType.SECOND_PLACE,
                            isReceived = false
                        ),
                        AwardNomination(
                            type = AwardType.THIRD_PLACE,
                            isReceived = true
                        )
                    )
                )
            )
        )
    }
}