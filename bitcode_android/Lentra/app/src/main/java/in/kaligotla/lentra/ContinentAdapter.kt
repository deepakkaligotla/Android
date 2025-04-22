package `in`.kaligotla.lentra

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import `in`.kaligotla.lentra.databinding.ViewHolderContinentBinding

class ContinentAdapter(private var continents: ArrayList<Continent>) : RecyclerView.Adapter<ContinentAdapter.ContinentViewHolder>() {
    inner class ContinentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var productsViewHolderBinding = ViewHolderContinentBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContinentViewHolder {
        return ContinentViewHolder(ViewHolderContinentBinding.inflate(LayoutInflater.from(parent.context)).root)
    }

    override fun onBindViewHolder(holder: ContinentViewHolder, position: Int) {
        holder.productsViewHolderBinding.codeTxtView.text = continents[position].code
        holder.productsViewHolderBinding.nameTxtView.text = continents[position].name
        holder.productsViewHolderBinding.areaSqKmTxtView.text = "${continents[position].areaSqKm}"
        holder.productsViewHolderBinding.populationTxtView.text = "${continents[position].population}"
        continents[position].lines.forEach {
            holder.productsViewHolderBinding.linesTxtView.append("$it ")
        }
        holder.productsViewHolderBinding.countriesTxtView.text = "${continents[position].countries}"
        continents[position].oceans.forEach {
            holder.productsViewHolderBinding.oceansTxtView.append("$it ")
        }
        continents[position].developedCountries.forEach {
            holder.productsViewHolderBinding.developedCountriesTxtView.append("$it ")
        }
    }

    override fun getItemCount(): Int {
        return continents.size
    }
}