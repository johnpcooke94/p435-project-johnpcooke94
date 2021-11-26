package edu.ius.streamdex.controllers

import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import edu.ius.streamdex.StreamRecyclerViewAdapter
import edu.ius.streamdex.api.StreamRepository
import edu.ius.streamdex.api.TwitchStream
import edu.ius.streamdex.models.Stream

class StreamListController {

    var streamList = mutableListOf<Stream>()
    private var streamResponse = mutableListOf<TwitchStream>()

    fun populateLiveStreams(owner: LifecycleOwner, view: RecyclerView) {
        streamList = mutableListOf()
        streamResponse = mutableListOf()
        StreamRepository.getStreams().observe(owner, { streams ->
            streamResponse.addAll(streams.data)
            transferResponseToList()
            view.adapter = StreamRecyclerViewAdapter(streamList)
        })
    }

    private fun transferResponseToList() {
        streamResponse.forEach {
            val newStream = Stream(it.title, null, it.type == "live")
            streamList.add(newStream)
        }
    }

}