package com.rachel.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rachel.notes.databinding.ActivityViewNoteBinding

class ViewNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewNoteBinding
    private lateinit var db: NotesDatabaseHelper
    private var noteid: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotesDatabaseHelper(this)

        noteid = intent.getIntExtra("note_id", -1)
        if (noteid == -1){
            finish()
            return
        }

        val note = db.getNoteByID(noteid)
        binding.tvNoteTitle.setText(note.title)
        binding.tvNoteContent.setText(note.content)

        binding.backButton.setOnClickListener {
            finish()
        }
        binding.updateButton.setOnClickListener {
            val intent = Intent(this, UpdateNoteActivity::class.java).apply {
                putExtra("note_id", note.id)
            }
            startActivity(intent)
        }


    }
}