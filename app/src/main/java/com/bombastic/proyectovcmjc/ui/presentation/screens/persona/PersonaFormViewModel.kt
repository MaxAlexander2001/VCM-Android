package com.bombastic.proyectovcmjc.ui.presentation.screens.persona

import androidx.lifecycle.*
import com.bombastic.proyectovcmjc.modelo.Persona
import com.bombastic.proyectovcmjc.repository.PersonaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonaFormViewModel @Inject constructor(
    private val personRepo: PersonaRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    fun getPersona(idX: Int):LiveData<Persona> {
        return personRepo.buscarPersonaId(idX)
    }
    val isLoading: LiveData<Boolean> get() = _isLoading
    fun addPersona(persona: Persona){
        viewModelScope.launch(Dispatchers.IO){
            personRepo.insertarPersona(persona)
        }
    }
    fun editPersona(persona: Persona){
        viewModelScope.launch(Dispatchers.IO){
            personRepo.modificarRemotePersona(persona)
        }
    }
}