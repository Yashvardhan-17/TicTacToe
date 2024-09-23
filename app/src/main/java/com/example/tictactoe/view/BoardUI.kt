package com.example.tictactoe.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.Logic.checkWinner

@Composable
fun TicTacToeGame(){

    var boardState by remember { mutableStateOf(List(3) {MutableList(3){""} }) }
    var currentPlayer by remember { mutableStateOf("X")  }
    var winner by remember { mutableStateOf<String?>(null)  }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        
        Text(text = "Tic Tac Toe", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        
        Spacer(modifier = Modifier.height(50.dp))

        for(i in 0..2){
            Row {
                for (j in 0..2){
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .background(Color.LightGray)
                            .clickable(enabled = boardState[i][j].isEmpty() && winner == null) {
                                if (boardState[i][j].isEmpty()) {
                                    val newBoardState = boardState
                                        .map { it.toMutableList() }
                                        .toMutableList()
                                    newBoardState[i][j] = currentPlayer
                                    boardState = newBoardState

                                    currentPlayer = if (currentPlayer == "X") "O" else "X"
                                    winner = checkWinner(boardState)
                                }
                            },
                        contentAlignment = Alignment.Center
                    ){
                        Text(text = boardState[i][j], fontSize = 36.sp)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        if(winner!=null){
            Text(text = "Winner:$winner", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }else if(boardState.flatten().none(){it.isEmpty()}){
            Text(text = "It's Draw ", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
            boardState = List(3){ MutableList(3){""} }
            winner=null
            currentPlayer="X"
        }) {
            Text(text = "Restart")
        }
    }
}

@Preview
@Composable
fun TicTacToeGamePreview(){
    TicTacToeGame()
}