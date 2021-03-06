/*
 * Copyright (c) 2022 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * These materials have been reviewed and are updated as of 7/2022.
 */

package com.yourcompany.android.creaturemon.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yourcompany.android.creaturemon.model.*
import com.yourcompany.android.creaturemon.model.room.RoomRepository

class CreatureViewModel(
    private val generator: CreatureGenerator = CreatureGenerator()
) : ViewModel() {

  private val creatureLiveData = MutableLiveData<Creature>()

  fun getCreatureLiveData(): LiveData<Creature> = creatureLiveData

  var name = ""
  var intelligence = 0
  var strength = 0
  var endurance = 0
  var drawable = 0

  lateinit var creature: Creature

  fun updateCreature() {
    val attributes = CreatureAttributes(intelligence, strength, endurance)
    creature = generator.generateCreature(attributes, name, drawable)
    creatureLiveData.postValue(creature)
  }

  fun attributeSelected(attributeType: AttributeType, position: Int) {
    when (attributeType) {
      AttributeType.INTELLIGENCE ->
        intelligence = AttributeStore.INTELLIGENCE[position].value
      AttributeType.STRENGTH ->
        strength = AttributeStore.STRENGTH[position].value
      AttributeType.ENDURANCE ->
        endurance = AttributeStore.ENDURANCE[position].value
    }
    updateCreature()
  }

  fun drawableSelected(drawable: Int) {
    this.drawable = drawable
    updateCreature()
  }
}