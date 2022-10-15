package com.example.journeyordestination.view

import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import com.example.journeyordestination.R
import com.google.android.material.textfield.TextInputLayout

class SwapDirections() {

    fun foo(){

    }

    companion object {

        fun swap(view: View) {

            val editText1: TextInputLayout = view.findViewById(R.id.first_text_field_layout)
            val editText2: TextInputLayout = view.findViewById(R.id.second_text_field_layout)
            val constraintLayout: ConstraintLayout =
                view.findViewById(R.id.rv_header_constraint_layout)
            val swapDirections: ImageView = view.findViewById(R.id.swap_directions)
            val completeEntry: ImageView = view.findViewById(R.id.complete_entry)

            editText1.updateLayoutParams<ConstraintLayout.LayoutParams> {
                // set editTexts1's lps to editText2's layout params

                //Undo top constraints
                bottomToTop = ConstraintLayout.LayoutParams.UNSET
                endToStart = ConstraintLayout.LayoutParams.UNSET
                startToStart = ConstraintLayout.LayoutParams.UNSET
                topToTop = ConstraintLayout.LayoutParams.UNSET

                //Move to bottom
                topToBottom = R.id.second_text_field_layout
                endToStart = R.id.swap_directions
                startToStart = constraintLayout.id
                bottomToBottom = constraintLayout.id

            }

            editText2.updateLayoutParams<ConstraintLayout.LayoutParams> {
                // set editText2's lps to editText1's layout params


                //Undo bottom constraints
                bottomToBottom = ConstraintLayout.LayoutParams.UNSET
                endToStart = ConstraintLayout.LayoutParams.UNSET
                startToStart = ConstraintLayout.LayoutParams.UNSET
                topToBottom = ConstraintLayout.LayoutParams.UNSET

                //Move to top
                bottomToTop = R.id.first_text_field_layout
                endToStart = R.id.complete_entry
                startToStart = constraintLayout.id
                topToTop = constraintLayout.id

            }

            swapDirections.updateLayoutParams<ConstraintLayout.LayoutParams> {

                //Keep at bottom
                bottomToBottom = editText1.id
                topToTop = editText1.id
                startToEnd = editText1.id
                endToEnd = constraintLayout.id

            }

            completeEntry.updateLayoutParams<ConstraintLayout.LayoutParams> {

                //Keep at top
                bottomToBottom = editText2.id
                topToTop = editText2.id
                startToEnd = editText2.id
                endToEnd = constraintLayout.id

            }
        }


        fun swapBack(view: View) {

            val editText1: TextInputLayout = view.findViewById(R.id.first_text_field_layout)
            val editText2: TextInputLayout = view.findViewById(R.id.second_text_field_layout)
            val constraintLayout: ConstraintLayout =
                view.findViewById(R.id.rv_header_constraint_layout)
            val swapDirections: ImageView = view.findViewById(R.id.swap_directions)
            val completeEntry: ImageView = view.findViewById(R.id.complete_entry)

            editText1.updateLayoutParams<ConstraintLayout.LayoutParams> {
                // set editTexts1's lps to editText2's layout params starting from the position of editText2 (BOTTOM)


                //Undo bottom constrains
                bottomToBottom = ConstraintLayout.LayoutParams.UNSET
                endToStart = ConstraintLayout.LayoutParams.UNSET
                startToStart = ConstraintLayout.LayoutParams.UNSET
                topToBottom = ConstraintLayout.LayoutParams.UNSET

                //Move to top
                bottomToTop = editText2.id
                endToStart = R.id.complete_entry
                startToStart = constraintLayout.id
                topToTop = constraintLayout.id

            }

            editText2.updateLayoutParams<ConstraintLayout.LayoutParams> {
                // set editText2's lps to editText1's layout params starting from the position of editText1 (TOP)

                //Undo top constraints
                bottomToTop = ConstraintLayout.LayoutParams.UNSET
                endToStart = ConstraintLayout.LayoutParams.UNSET
                startToStart = ConstraintLayout.LayoutParams.UNSET
                topToTop = ConstraintLayout.LayoutParams.UNSET

                //Move to bottom
                topToBottom = editText1.id
                endToStart = R.id.swap_directions
                startToStart = constraintLayout.id
                bottomToBottom = constraintLayout.id

            }


            completeEntry.updateLayoutParams<ConstraintLayout.LayoutParams> {

                //Keep at top
                bottomToBottom = editText1.id
                topToTop = editText1.id
                startToEnd = editText1.id
                endToEnd = constraintLayout.id


            }

            swapDirections.updateLayoutParams<ConstraintLayout.LayoutParams> {

                //Keep at bottom
                bottomToBottom = editText2.id
                topToTop = editText2.id
                startToEnd = editText2.id
                endToEnd = constraintLayout.id

            }
        }


    }


}

