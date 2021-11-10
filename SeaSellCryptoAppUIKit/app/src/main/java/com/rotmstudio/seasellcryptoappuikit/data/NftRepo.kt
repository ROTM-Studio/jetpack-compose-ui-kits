package com.rotmstudio.seasellcryptoappuikit.data

object NftRepo {
    fun getNfts(): List<Nft> = nfts
    fun getNft(nftId: Long): Nft = nfts.find { it.id == nftId }!!
}