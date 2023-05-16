/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringServer;

import com.google.ortools.Loader;
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import org.graph4j.Digraph;
import org.graph4j.alg.Tournament;
import org.graph4j.generate.TournamentGenerator;

/**
 *
 * @author Vlad Adriana
 */
public class TournamentManager {
    MPSolver.ResultStatus resultStatus;
    int n,p,d;
    Digraph graph;
    boolean[][][] schedule;
    public TournamentManager(int n, int p, int d){
        
        this.n=n;
        this.p=p;
        this.d=d;
        schedule = new boolean[n][n][d];
    }
    public void generateOutcomes(){
        TournamentGenerator tg = new TournamentGenerator(n);
        graph = tg.createRandom();
    }
    public Digraph getOutcomeGraph(){
        return graph;
    }
    public boolean generateSchedule(){
        Loader.loadNativeLibraries();
        MPSolver solver = MPSolver.createSolver("GLOP");
        MPVariable[][][] x = new MPVariable[n][n][d];
        int i,j,k;
        for(i=0;i<n;i++)
            for(j=0;j<n;j++)
                for(k=0;k<d;k++)
                    x[i][j][k] = solver.makeIntVar(0,1,"x"+i+j+k);
        for(i=0;i<n;i++)
            for(j=i+1;j<n;j++){
                MPConstraint cSum = solver.makeConstraint(1,1);
                for(k=0;k<d;k++)
                    cSum.setCoefficient(x[i][j][k],1);
            }
        for(i=0;i<n;i++)
            for(k=0;k<d;k++){
                MPConstraint cSum = solver.makeConstraint(0,p);
                for(j=i+1;j<n;j++)
                    cSum.setCoefficient(x[i][j][k], 1);
            }
        resultStatus = solver.solve();
        if (resultStatus == MPSolver.ResultStatus.OPTIMAL){
            for(i=0;i<n;i++)
                for(j=i+1;j<n;j++)
                    for(k=0;k<d;k++){
                        if(x[i][j][k].solutionValue()==1)
                            schedule[i][j][k]=true;
                        schedule[j][i][k]=schedule[i][j][k];
                    }
            return true;
        }
        else return false;
    }
    public boolean[][][] getSchedule(){
        return schedule;
    }
    public String displayRankings(){
        Tournament tournament = new Tournament(graph);
        return (tournament.getHamiltonianPath().toString());
    }
    public String displaySchedule(){
        String response = new String();
        int i,j,k;
        for(i=0;i<n;i++)
            for(j=i+1;j<n;j++)
                for(k=0;k<d;k++)
                    if(schedule[i][j][k]==true)
                        response =response +"Player"+(i+1)+" vs Player"+(j+1)+": Day "+(k+1)+"\n";
        return response;
    }
}
